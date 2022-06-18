package ru.dw.starvars.presenter.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.dw.starvars.databinding.FragmentListPeoplsBinding

class ListPeoplesFragment : Fragment() {

    companion object {
        fun newInstance() = ListPeoplesFragment()
    }
    private var _binding:FragmentListPeoplsBinding? = null
    private val binding get() = _binding!!

    private  val viewModel: ListPeoplesViewModel by lazy {
        ViewModelProvider(this)[ListPeoplesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListPeoplsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}