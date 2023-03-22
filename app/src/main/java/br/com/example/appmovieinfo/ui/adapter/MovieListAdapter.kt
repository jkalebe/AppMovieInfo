package br.com.example.appmovieinfo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.example.appmovieinfo.R
import br.com.example.appmovieinfo.databinding.ItemMovieBinding
import br.com.example.appmovieinfo.model.Movie
import com.squareup.picasso.Picasso

class MovieListAdapter(
    private val items: List<Movie>,
    private val onItemClick: (Movie) -> Unit
    ): RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layout = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieHolder(layout)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = items[position]
        if(movie.Poster != null){
            Picasso.get().load(movie.Poster).into(
                holder.imgCover
            )
        } else {
            holder.imgCover.setImageResource(R.drawable.ic_broken_image)
        }

        holder.txtTitle.text = movie.Title
        holder.txtType.text = movie.Type
        holder.txtYear.text = movie.Year
        holder.itemView.setOnClickListener{
            onItemClick(movie)
        }
    }

    override fun getItemCount(): Int = items.size

    class MovieHolder(binding: ItemMovieBinding):RecyclerView.ViewHolder(binding.root){
        val imgCover: ImageView = binding.imgCover
        val txtTitle: TextView = binding.txtTitle
        val txtType: TextView = binding.txtType
        val txtYear: TextView = binding.txtYear
    }
}