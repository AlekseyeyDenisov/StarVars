package ru.dw.starvars.presenter.list


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.dw.starvars.databinding.FragmentListPeoplsBinding
import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.presenter.list.recycler.AdapterRecyclerListPeoples
import ru.dw.starvars.presenter.list.recycler.OnItemClickListener
import ru.dw.utils.START_PEOPLES_LIST_URL
import kotlin.math.log

class ListPeoplesFragment : Fragment(), OnItemClickListener {

    companion object {
        fun newInstance() = ListPeoplesFragment()
    }

    private var _binding: FragmentListPeoplsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListPeoplesViewModel by lazy {
        ViewModelProvider(this)[ListPeoplesViewModel::class.java]
    }

    private val adapterRecyclerListPeoples = AdapterRecyclerListPeoples(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListPeoplsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestStart()
        initObserve()
        initRecycler(binding.listPeopleRecyclerView)

    }

    private fun initRecycler(listPeopleRecyclerView: RecyclerView) {
        with(listPeopleRecyclerView){
            adapter = adapterRecyclerListPeoples
        }
    }

    private fun requestStart() {
        viewModel.upDataListPeople(START_PEOPLES_LIST_URL)
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
                visibilityProgress(false)
                adapterRecyclerListPeoples.submitList(state.peopleList)

            }
            is ListState.Error -> {
                visibilityProgress(false)
                Log.d("@@@", "renderData Error ${state.error}")
            }
        }

    }

    private fun visibilityProgress(visibility: Boolean) {
        if (visibility)binding.showProgress.visibility = View.VISIBLE
        else binding.showProgress.visibility = View.GONE

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(peoplesItemView: PeoplesItemView) {
        Log.d("@@@", "onItemClick name: " +peoplesItemView.name)

    }

    override fun onItemClickLoadMore(peoplesItemView: PeoplesItemView) {
        Log.d("@@@", "onItemClick name: " +peoplesItemView.nextPage)
    }


}