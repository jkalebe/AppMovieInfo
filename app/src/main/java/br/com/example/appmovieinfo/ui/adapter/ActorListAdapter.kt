package br.com.example.appmovieinfo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.example.appmovieinfo.databinding.ItemActorBinding
import br.com.example.appmovieinfo.model.Actor

class ActorListAdapter(
    private val actors:List<Actor>
): RecyclerView.Adapter<ActorListAdapter.ActorViewHolder>() {

    class ActorViewHolder(val binding: ItemActorBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: Actor){
            binding.actor = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val inflater =LayoutInflater.from(parent.context)

        val listItemBinding =ItemActorBinding.inflate(inflater, parent, false)

        return ActorViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor = actors[position]
        holder.bind(actor)
    }

    override fun getItemCount(): Int=actors.size
}