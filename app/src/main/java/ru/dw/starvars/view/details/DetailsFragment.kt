package ru.dw.starvars.view.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.dw.starvars.R
import ru.dw.starvars.domain.model.PeoplesItemView

class DetailsFragment : Fragment() {


    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val peoplesItemView = arguments?.getParcelable<PeoplesItemView>(BUNDLE_DETAILS)
        Log.d("@@@", "onViewCreated: $peoplesItemView")
    }

    companion object {
        private const val BUNDLE_DETAILS = "key_bundle_details"
        fun bundleDetails(peoplesItemView: PeoplesItemView):Bundle{
           return Bundle().apply {
               putParcelable(BUNDLE_DETAILS,peoplesItemView)
           }
        }

        fun newInstance(bundle: Bundle): DetailsFragment{
            return DetailsFragment().apply {
                arguments = bundle
            }
        }
    }


}