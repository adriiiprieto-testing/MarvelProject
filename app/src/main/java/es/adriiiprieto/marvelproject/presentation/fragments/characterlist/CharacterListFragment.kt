package es.adriiiprieto.marvelproject.presentation.fragments.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import es.adriiiprieto.marvelproject.databinding.FragmentCharacterListBinding

class CharacterListFragment : Fragment() {

    lateinit var binding: FragmentCharacterListBinding

    val viewModel: CharacterListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentCharacterListBinding.inflate(layoutInflater, container, false)

        viewModel.requestInformation()

        return binding.root

    }

}