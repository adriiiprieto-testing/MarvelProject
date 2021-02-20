package es.adriiiprieto.marvelproject.presentation.fragments.characterdetail.curriculum

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.adriiiprieto.marvelproject.databinding.ItemCurriculumBinding

class CharacterDetailRecyclerViewAdapter(private var dataSet: List<String>) : RecyclerView.Adapter<CharacterDetailRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCurriculumBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCurriculumBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]

        viewHolder.binding.apply {
            itemCurriculumTextView.text = item
        }
    }

    override fun getItemCount() = dataSet.size
}