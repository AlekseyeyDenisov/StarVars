package ru.dw.starvars.pressentation.view.details


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.dw.starvars.StartWarsApp
import ru.dw.starvars.databinding.FragmentDetailsBinding
import ru.dw.starvars.domain.model.CharacterItemView
import ru.dw.starvars.pressentation.ViewModelFactory
import javax.inject.Inject


class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentDetailsBinding = null ")


    private lateinit var viewModel: DetailsViewModel

    private val component by lazy {
        (requireActivity().application as StartWarsApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(false)
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[DetailsViewModel::class.java]

        val characterItemView = arguments?.getParcelable<CharacterItemView>(BUNDLE_DETAILS)
        render(characterItemView)

    }

    private fun render(characterItemView: CharacterItemView?) {
        initViewDataBundle(characterItemView)

        characterItemView?.homeWorld?.let { it ->
            viewModel.getNameAttr(it, CONSTANT_ATTRIBUTE_HOME_WORLD) { name ->
                binding.homeWorld.text = name
            }
        }
    }

    private fun initViewDataBundle(characterItemView: CharacterItemView?) {
        binding.nameCharacter.text = characterItemView?.name
        characterItemView?.height?.let { binding.height.text = it }
        characterItemView?.mass?.let { binding.mass.text = it }
        characterItemView?.hairColor?.let { binding.hairColor.text = it }
        characterItemView?.skinColor?.let { binding.skinColor.text = it }
        characterItemView?.eyeColor?.let { binding.eyeColor.text = it }
        characterItemView?.birthYear?.let { binding.birthYear.text = it }
        characterItemView?.gender?.let { binding.gender.text = it }
    }

    companion object {
        private const val BUNDLE_DETAILS = "key_bundle_details"
        const val CONSTANT_ATTRIBUTE_HOME_WORLD = "homeworld"
        fun bundleDetails(characterItemView: CharacterItemView): Bundle {
            return Bundle().apply {
                putParcelable(BUNDLE_DETAILS, characterItemView)
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