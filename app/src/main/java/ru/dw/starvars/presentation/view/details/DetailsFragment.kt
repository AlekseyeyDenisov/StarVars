package ru.dw.starvars.presentation.view.details


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.dw.starvars.databinding.FragmentDetailsBinding
import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.presentation.MainActivity
import ru.dw.starvars.utils.NetworkUtil
import ru.dw.starvars.presentation.viewmodel.details.DetailsViewModel


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
        initViewDataBundle(peoplesItemView)

        peoplesItemView?.homeWorld?.let { it ->
            viewModel.getNameAttr(it,isInternet()){ name->
                binding.homeWorld.text = name
            }
        }
    }

    private fun initViewDataBundle(peoplesItemView: PeoplesItemView?) {
        binding.nameCharacter.text = peoplesItemView?.name
        peoplesItemView?.height?.let { binding.height.text = it }
        peoplesItemView?.mass?.let { binding.mass.text = it }
        peoplesItemView?.hairColor?.let { binding.hairColor.text = it }
        peoplesItemView?.skinColor?.let { binding.skinColor.text = it }
        peoplesItemView?.eyeColor?.let { binding.eyeColor.text = it }
        peoplesItemView?.birthYear?.let { binding.birthYear.text = it }
        peoplesItemView?.gender?.let { binding.gender.text = it }
    }

    companion object {
        private const val BUNDLE_DETAILS = "key_bundle_details"

        fun newInstance(peoplesItemView: PeoplesItemView): DetailsFragment {
            return DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(BUNDLE_DETAILS, peoplesItemView)
                }
            }
        }
    }
    private fun isInternet(): Boolean {
        return NetworkUtil.getConnectivityStatus(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onResume() {
        super.onResume()
        showHideActionBar(false)
    }

    override fun onPause() {
        super.onPause()
        showHideActionBar(true)
    }

    private fun showHideActionBar(visibility:Boolean) {
        if (visibility) {
            (requireActivity() as MainActivity).supportActionBar?.show()
        } else {
            (requireActivity() as MainActivity).supportActionBar?.hide()
        }
    }
}