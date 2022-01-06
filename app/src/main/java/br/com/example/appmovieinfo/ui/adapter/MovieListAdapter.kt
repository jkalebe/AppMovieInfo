package br.com.example.appmovieinfo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.example.appmovieinfo.R
import br.com.example.appmovieinfo.model.Movie
import br.com.example.appmovieinfo.model.MovieInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter(
    private val items: List<Movie>,
    private val onItemClick: (Movie) -> Unit
    ): RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
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

    class MovieHolder(rootView: View):RecyclerView.ViewHolder(rootView){
        val imgCover: ImageView = rootView.imgCover
        val txtTitle: TextView = rootView.txtTitle
        val txtType: TextView = rootView.txtType
        val txtYear: TextView = rootView.txtYear
    }
}