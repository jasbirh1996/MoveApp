package com.jasbir.movie.ui.View.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jasbir.movie.data.dataclass.NowPlayingResponse
import com.jasbir.movie.R
import com.jasbir.movie.ui.View.UserInterface.DetailActivity

class NowPlayingMovieAdapter  (var movieItem : List<NowPlayingResponse.Result>) : RecyclerView.Adapter<NowPlayingMovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


     val view =    LayoutInflater.from(parent.context).inflate(R.layout.nowplayingmovieitem,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.title.text = movieItem.get(position)?.title.toString()
        holder.rating.text = movieItem.get(position)?.vote_average.toString()
        var image  = movieItem.get(position)?.poster_path.toString()
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500/$image")
            .into(holder.posterImage)


        holder.itemview.setOnClickListener {

            val id = movieItem.get(position).id.toString()
            val intent = Intent(holder.itemview.context,DetailActivity::class.java)
            intent.putExtra("id",id)
            holder.itemview.context.startActivity(intent)
        }
    }

    inner class ViewHolder(var itemview : View)
        : RecyclerView.ViewHolder(itemview) {

        var posterImage : ImageView
        var rating : TextView
        var title : TextView

        init {
            posterImage = itemview.findViewById(R.id.posterImage)
            rating = itemview.findViewById(R.id.review)
            title = itemview.findViewById(R.id.title)
        }



    }

    override fun getItemCount(): Int {
      return  movieItem.size
    }


}
