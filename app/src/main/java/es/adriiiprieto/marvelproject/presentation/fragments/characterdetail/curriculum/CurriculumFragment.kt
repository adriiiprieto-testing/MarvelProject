package es.adriiiprieto.marvelproject.presentation.fragments.characterdetail.curriculum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import es.adriiiprieto.marvelproject.data.marvel.model.Item
import es.adriiiprieto.marvelproject.databinding.FragmentCurriculumBinding


class CurriculumFragment(private val items: List<Item>, private val showButton: Boolean = false, private val myListener: (comic: String) -> Unit) : Fragment() {

    lateinit var binding: FragmentCurriculumBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentCurriculumBinding.inflate(inflater, container, false)

        setupView()

        return binding.root
    }

    private fun setupView() {
        val listToShow: MutableList<String> = mutableListOf()
        items.forEach { listToShow.add(it.name) }

        binding.fragmentCurriculumRecyclerView.apply {
            adapter = CharacterDetailRecyclerViewAdapter(listToShow, showButton, myListener)
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

}