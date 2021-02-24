package es.adriiiprieto.marvelproject.presentation.fragments.characterlist

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
import es.adriiiprieto.marvelproject.databinding.FragmentCharacterListBinding

class CharacterListFragment : BaseFragment<CharacterListState, CharacterListViewModel, FragmentCharacterListBinding>() {

    /**
     * Base classes variables
     */
    override val viewModelClass = CharacterListViewModel::class.java

    lateinit var vm: CharacterListViewModel

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharacterListBinding = FragmentCharacterListBinding::inflate

    /**
     * Custom variables
     */
    lateinit var mAdapter: CharacterListAdapter

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

        setSpinnerListener(true)
    }

    private fun setSpinnerListener(selectable: Boolean) {
        if (selectable) {
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
        } else {
            binding.fragmentCharacterListSpinner.onItemSelectedListener = null
        }
    }

    /**
     * State management functions
     */
    override fun onNormal(data: CharacterListState) {
        setSpinnerListener(false)
        mAdapter.updateList(data.characterList)
        val list = resources.getStringArray(R.array.fragment_character_list_spinner_array)
        binding.fragmentCharacterListSpinner.setSelection(list.indexOf(data.limit.toString()))
        setSpinnerListener(true)
    }

    override fun onLoading(dataLoading: BaseExtraData?) {

    }

    override fun onError(dataError: Throwable) {

    }
}