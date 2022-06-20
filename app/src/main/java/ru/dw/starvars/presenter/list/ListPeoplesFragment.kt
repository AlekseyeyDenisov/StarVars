package ru.dw.starvars.presenter.list


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.dw.starvars.databinding.FragmentListPeoplsBinding
import ru.dw.utils.START_PEOPLES_LIST_URL

class ListPeoplesFragment : Fragment() {

    companion object {
        fun newInstance() = ListPeoplesFragment()
    }

    private var _binding: FragmentListPeoplsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListPeoplesViewModel by lazy {
        ViewModelProvider(this)[ListPeoplesViewModel::class.java]
    }

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
                Log.d("@@@", "renderData Loading: ")
            }
            is ListState.Success -> {
                state.peopleList.forEach {
                    Log.d("@@@", "renderData Success: ${it.name}")
                }

            }
            is ListState.Error -> {
                Log.d("@@@", "renderData Error ${state.error}")
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}