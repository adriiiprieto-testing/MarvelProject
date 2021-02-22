package es.adriiiprieto.marvelproject.presentation.fragments.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import es.adriiiprieto.marvelproject.R
import es.adriiiprieto.marvelproject.base.BaseExtraData
import es.adriiiprieto.marvelproject.base.BaseFragment
import es.adriiiprieto.marvelproject.base.BaseState
import es.adriiiprieto.marvelproject.databinding.FragmentCharacterListBinding

class CharacterListFragment : BaseFragment<CharacterListViewModel, FragmentCharacterListBinding>() {

    /**
     * Base classes variables
     */
    override val viewModelClass = CharacterListViewModel::class.java

    lateinit var vm: CharacterListViewModel

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharacterListBinding = FragmentCharacterListBinding::inflate



    /**
     * Base class methods
     */
    override fun setupView(viewModel: CharacterListViewModel) {
        vm = viewModel

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
                    vm.onActionChangeSpinnerValue(20.toString())
                } else {
                    vm.onActionChangeSpinnerValue(parent.getItemAtPosition(pos).toString())
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }


    /**
     * Old methods
     */

    lateinit var mAdapter: CharacterListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        vm.getState().observe(viewLifecycleOwner, { state ->
            when (state) {
                is BaseState.Normal -> onNormal(state.data as CharacterListState)
                is BaseState.Loading -> onLoading(state.dataLoading)
                is BaseState.Error -> onError(state.dataError)
            }
        })

        vm.requestInformation()

        return binding.root
    }


    private fun onNormal(characterListState: CharacterListState) {
        mAdapter.updateList(characterListState.characterList)
    }

    private fun onLoading(dataLoading: BaseExtraData?) {

    }

    private fun onError(dataError: Throwable) {

    }


}