package es.adriiiprieto.marvelproject.presentation.fragments.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import es.adriiiprieto.marvelproject.R
import es.adriiiprieto.marvelproject.base.BaseExtraData
import es.adriiiprieto.marvelproject.base.BaseState
import es.adriiiprieto.marvelproject.databinding.FragmentCharacterListBinding

class CharacterListFragment : Fragment() {

    lateinit var binding: FragmentCharacterListBinding

    val viewModel: CharacterListViewModel by viewModels()

    lateinit var mAdapter: CharacterListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentCharacterListBinding.inflate(layoutInflater, container, false)

        viewModel.getState().observe(viewLifecycleOwner, { state ->
            when (state) {
                is BaseState.Normal -> onNormal(state.data as CharacterListState)
                is BaseState.Loading -> onLoading(state.dataLoading)
                is BaseState.Error -> onError(state.dataError)
            }
        })

        setupView()

        viewModel.requestInformation()

        return binding.root

    }


    private fun setupView() {
        // Setup recycler view
        mAdapter = CharacterListAdapter(listOf(), requireActivity()) { character ->
            findNavController().navigate(CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(character.id))
        }
        binding.fragmentCharacterListRecyclerView.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(requireActivity(), 2)
            itemAnimator = DefaultItemAnimator()
        }

        // Setup spinner
        ArrayAdapter.createFromResource(requireActivity(), R.array.fragment_character_list_spinner_array, android.R.layout.simple_spinner_item).also { spinnerAdapter ->
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.fragmentCharacterListSpinner.adapter = spinnerAdapter
        }

        binding.fragmentCharacterListSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                if (pos == 0) {
                    viewModel.onActionChangeSpinnerValue(20.toString())
                } else {
                    viewModel.onActionChangeSpinnerValue(parent.getItemAtPosition(pos).toString())
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun onNormal(characterListState: CharacterListState) {
        mAdapter.updateList(characterListState.characterList)
    }

    private fun onLoading(dataLoading: BaseExtraData?) {

    }

    private fun onError(dataError: Throwable) {

    }

}