package ru.dw.starvars.view.list


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.dw.starvars.R
import ru.dw.starvars.databinding.FragmentListPeoplsBinding
import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.utils.NetworkUtil
import ru.dw.starvars.utils.START_PEOPLES_LIST_URL
import ru.dw.starvars.utils.SharedPreferencesManager
import ru.dw.starvars.view.details.DetailsFragment
import ru.dw.starvars.view.list.recycler.AdapterRecyclerListPeoples
import ru.dw.starvars.view.list.recycler.OnItemClickListener
import ru.dw.starvars.viewmodel.list.ListPeoplesViewModel

class ListPeoplesFragment : Fragment(), OnItemClickListener {
    private lateinit var pref: SharedPreferencesManager

    companion object {
        fun newInstance() = ListPeoplesFragment()
    }

    private var _binding: FragmentListPeoplsBinding? = null
    private val binding get() = _binding!!
    private var listRecycler: List<PeoplesItemView> = ArrayList()

    private val viewModel: ListPeoplesViewModel by lazy {
        ViewModelProvider(this)[ListPeoplesViewModel::class.java]
    }

    private val adapterRecyclerListPeoples = AdapterRecyclerListPeoples(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListPeoplsBinding.inflate(inflater, container, false)
        pref = SharedPreferencesManager(requireContext())
        return binding.root
    }

    override fun onResume() {
        setHasOptionsMenu(true)
        super.onResume()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstStart()
        initObserve()
        initRecycler(binding.listPeopleRecyclerView)

    }

    private fun initRecycler(listPeopleRecyclerView: RecyclerView) {
        with(listPeopleRecyclerView) {
            adapter = adapterRecyclerListPeoples
        }
    }

    private fun firstStart() {
        if (pref.getFirstStart()) {
            viewModel.requestUrl(START_PEOPLES_LIST_URL)
        }
    }

    private fun initObserve() {
        viewModel.getLivedata().observe(viewLifecycleOwner) { state ->
            renderData(state)
            if (pref.getFirstStart()) pref.setFirstStart(false)
        }
    }

    private fun renderData(state: ListState) {

        when (state) {
            is ListState.Loading -> {
                visibilityProgress(true)

            }
            is ListState.Success -> {
                if (state.peopleList.isNotEmpty())
                    visibilityProgress(false)

                listRecycler = state.peopleList
                adapterRecyclerListPeoples.submitList(state.peopleList)
            }
            is ListState.Error -> {
                visibilityProgress(false)
                Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun visibilityProgress(visibility: Boolean) {
        if (visibility) binding.showProgress.visibility = View.VISIBLE
        else binding.showProgress.visibility = View.GONE

    }

    override fun onItemClick(peoplesItemView: PeoplesItemView) {
        val bundle = DetailsFragment.bundleDetails(peoplesItemView)

        if (isOnePanelMode()){
            launchFragment(DetailsFragment.newInstance(bundle),R.id.container)


        }else{
            launchFragment(DetailsFragment.newInstance(bundle),R.id.detailsContainer)
        }
    }
    private fun launchFragment(fragment: Fragment, containerId: Int) {
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.enter_right_to_left,
                R.anim.exit_right_to_left,
                R.anim.enter_left_to_right,
                R.anim.exit_left_to_right,

            )
            .add(containerId, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onItemClickLoadMore(peoplesItemView: PeoplesItemView) {
        if (icConnect())
            peoplesItemView.nextPage?.let {
                viewModel.requestUrl(it)
            }
        else Toast.makeText(
            requireContext(),
            getString(R.string.no_internet_connection),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val itemSearch = menu.findItem(R.id.search)
        val search = itemSearch.actionView as SearchView

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                val filterData = listRecycler.filter {
                    it.name.lowercase().contains(newText.lowercase())
                }
                //Добавляем load more
                val lastItem = listRecycler.last()
                val mutableListData = filterData.toMutableList()
                mutableListData.add(lastItem)

                adapterRecyclerListPeoples.submitList(mutableListData)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> {
                visibilityProgress(true)
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.refresh()
                    launch(Dispatchers.Main) {
                        viewModel.requestUrl(START_PEOPLES_LIST_URL)
                    }
                }
            }
        }
        return false
    }

    private fun isOnePanelMode():Boolean{
        return binding.detailsContainer == null
    }


    private fun icConnect(): Boolean {
        return NetworkUtil.getConnectivityStatusString(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}