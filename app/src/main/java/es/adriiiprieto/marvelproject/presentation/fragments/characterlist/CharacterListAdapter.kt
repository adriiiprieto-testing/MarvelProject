package es.adriiiprieto.marvelproject.presentation.fragments.characterlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.adriiiprieto.marvelproject.R
import es.adriiiprieto.marvelproject.data.model.Character
import es.adriiiprieto.marvelproject.databinding.ItemCharacterListBinding

class CharacterListAdapter(private var dataSet: List<Character>, private val context: Context, private val callback: (item: Character) -> Unit) : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCharacterListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCharacterListBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]

        viewHolder.binding.apply {
            itemCharacterListTextViewName.text = item.name

            val url = item.thumbnail.path.replace("http", "https") + "." + item.thumbnail.extension
            Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_loading)
                .into(itemCharacterListImageViewLogo)
        }

        viewHolder.itemView.setOnClickListener {
            callback.invoke(item)
        }
    }

    override fun getItemCount() = dataSet.size

    fun updateList(newList: List<Character>){
        dataSet = newList
        notifyDataSetChanged()
    }
}