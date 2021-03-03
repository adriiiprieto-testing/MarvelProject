package es.adriiiprieto.marvelproject.presentation.fragments.characterdetail.curriculum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.adriiiprieto.marvelproject.databinding.ItemCurriculumBinding

class CharacterDetailRecyclerViewAdapter(private var dataSet: List<String>, private val showButton: Boolean = false, private val myListener: (comic: String) -> Unit) : RecyclerView.Adapter<CharacterDetailRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCurriculumBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCurriculumBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]

        viewHolder.binding.apply {
            itemCurriculumTextView.text = item
            itemCurriculumButtonSee.visibility = if (showButton) View.VISIBLE else View.GONE
            itemCurriculumButtonSee.setOnClickListener {
                myListener.invoke(item)
            }
        }
    }

    override fun getItemCount() = dataSet.size
}