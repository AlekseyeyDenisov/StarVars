package ru.dw.starvars.view.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.data.retrofit.model.PlanetsPojo
import ru.dw.starvars.databinding.FragmentDetailsBinding
import ru.dw.starvars.domain.model.PeoplesItemView

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!


    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this)[DetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val peoplesItemView = arguments?.getParcelable<PeoplesItemView>(BUNDLE_DETAILS)
        render(peoplesItemView)
    }

    private fun render(peoplesItemView: PeoplesItemView?) {
        Log.d("@@@", "render: $peoplesItemView")
        binding.nameCharacter.text = peoplesItemView?.name

        peoplesItemView?.height?.let { binding.height.text = it }
        peoplesItemView?.mass?.let { binding.mass.text = it }
        peoplesItemView?.hairColor?.let { binding.hairColor.text = it }
        peoplesItemView?.skinColor?.let { binding.skinColor.text = it }
        peoplesItemView?.eyeColor?.let { binding.eyeColor.text = it }
        peoplesItemView?.birthYear?.let { binding.birthYear.text = it }
        peoplesItemView?.gender?.let { binding.gender.text = it }

        peoplesItemView?.homeWorld?.let { RetrofitApiStarWars.getPlanetRequestUrl(it,object:
            RetrofitApiStarWars.GenericCallBackRetrofit<PlanetsPojo>{
            override fun success(pogo: PlanetsPojo) {
                binding.homeWorld.text = pogo.name
            }

            override fun error(error: String) {
                TODO("Not yet implemented")
            }

        }) }

    }


    companion object {
        private const val BUNDLE_DETAILS = "key_bundle_details"
        fun bundleDetails(peoplesItemView: PeoplesItemView): Bundle {
            return Bundle().apply {
                putParcelable(BUNDLE_DETAILS, peoplesItemView)
            }
        }

        fun newInstance(bundle: Bundle): DetailsFragment {
            return DetailsFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}