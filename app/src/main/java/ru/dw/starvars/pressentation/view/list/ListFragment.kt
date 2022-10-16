package ru.dw.starvars.pressentation.view.list


import android.content.Context
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
import ru.dw.starvars.StartWarsApp
import ru.dw.starvars.data.SharedPreferencesManager
import ru.dw.starvars.databinding.FragmentListPeoplsBinding
import ru.dw.starvars.domain.model.CharacterItemView
import ru.dw.starvars.pressentation.ViewModelFactory
import ru.dw.starvars.pressentation.view.details.DetailsFragment
import ru.dw.starvars.pressentation.view.list.recycler.AdapterRecyclerListCharacters
import ru.dw.starvars.pressentation.view.list.recycler.OnItemClickListener
import ru.dw.starvars.utils.NetworkUtil
import javax.inject.Inject

class ListFragment : Fragment(), OnItemClickListener {
    private lateinit var pref: SharedPreferencesManager

    companion object {
        fun newInstance() = ListFragment()
        private const val START_CHAPTERS_LIST_URL = "https://swapi.dev/api/people/"

    }

    private var _binding: FragmentListPeoplsBinding? = null
    private val binding get() = _binding!!
    private var listRecycler: List<CharacterItemView> = ArrayList()

    private lateinit var viewModel: ListViewModel

    private val component by lazy {
        (requireActivity().application as StartWarsApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val adapterRecyclerListCharacters = AdapterRecyclerListCharacters(this)

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListPeoplsBinding.inflate(inflater, container, false)
        pref = SharedPreferencesManager(requireContext())
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this, viewModelFactory)[ListViewModel::class.java]


        initRecycler(binding.listChapterRecyclerView)
        firstStart()
        initObserve()

    }

    private fun initRecycler(listPeopleRecyclerView: RecyclerView) {
        with(listPeopleRecyclerView) {
            adapter = adapterRecyclerListCharacters
        }
    }

    private fun firstStart() {
        if (pref.getFirstStart()) {
            if (isConnectInternet()) {
                viewModel.requestUrl(START_CHAPTERS_LIST_URL)
                pref.setFirstStart(false)
            }
        }
    }

    private fun initObserve() {
        viewModel.getLivedata().observe(viewLifecycleOwner) { state ->
            renderData(state)
        }
    }

    private fun renderData(state: ListState) {

        when (state) {
            is ListState.Loading -> {
                visibilityProgress(true)

            }
            is ListState.Success -> {
                if (state.chapterList.isNotEmpty())
                    visibilityProgress(false)

                listRecycler = state.chapterList
                adapterRecyclerListCharacters.submitList(state.chapterList)
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

    override fun onItemClick(characterItemView: CharacterItemView) {
        val bundle = DetailsFragment.bundleDetails(characterItemView)

        if (isOnePanelMode()) {
            launchFragment(DetailsFragment.newInstance(bundle), R.id.container)


        } else {
            launchFragment(DetailsFragment.newInstance(bundle), R.id.detailsContainer)
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

    override fun onItemClickLoadMore(characterItemView: CharacterItemView) {
        if (isConnectInternet())
            characterItemView.nextPage?.let {
                viewModel.requestUrl(it)
            }
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

                adapterRecyclerListCharacters.submitList(mutableListData)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> {
                if (isConnectInternet()) {
                    visibilityProgress(true)
                    lifecycleScope.launch(Dispatchers.IO) {
                        viewModel.refresh()
                        launch(Dispatchers.Main) {
                            viewModel.requestUrl(START_CHAPTERS_LIST_URL)
                        }
                    }
                }
            }
        }
        return false
    }

    private fun isOnePanelMode(): Boolean {
        return binding.detailsContainer == null
    }


    private fun isConnectInternet(): Boolean {
        return NetworkUtil.getConnectivityStatusString(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}