package com.jasbir.flix.View.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jasbir.flix.Model.dataclass.NowPlayingResponse
import com.jasbir.flix.R
import com.jasbir.flix.View.UserInterface.DetailActivity
import timber.log.Timber

class TrendingMovieAdapter (var movieItem : List<NowPlayingResponse.Result>) : RecyclerView.Adapter<TrendingMovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        val view =    LayoutInflater.from(parent.context).inflate(R.layout.trendingitem,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var image = movieItem.get(position).poster_path.toString()

        Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/w500/$image").into(holder.posterImage)
         holder.title.text = movieItem.get(position).title
        holder.rating.text = movieItem.get(position).vote_average.toString()

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
            posterImage = itemview.findViewById(R.id.tPoster)
            rating = itemview.findViewById(R.id.review)
            title = itemview.findViewById(R.id.tTitle)

        }



    }

    override fun getItemCount(): Int {
        return  movieItem.size
    }


}