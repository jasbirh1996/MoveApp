package com.jasbir.movie.ui.View.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jasbir.movie.data.dataclass.NowPlayingResponse
import com.jasbir.movie.R
import com.jasbir.movie.ui.View.UserInterface.DetailActivity

class ViewMoreAdapter() :
    PagingDataAdapter<NowPlayingResponse.Result, ViewMoreAdapter.ViewHolder>(DataDifferntiator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemviewmore, parent, false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var image = getItem(position)?.poster_path.toString()
        Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/w500/$image").into(holder.img)

      holder.itemView.setOnClickListener {
          val id = getItem(position)?.id.toString()
          val intent = Intent(holder.itemView.context, DetailActivity::class.java)
          intent.putExtra("id",id)
          holder.itemView.context.startActivity(intent)
      }

    }

    inner class ViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {

        var img : ImageView
        init {

            img = itemView.findViewById(R.id.imageViewAll)
        }


    }


    object DataDifferntiator : DiffUtil.ItemCallback<NowPlayingResponse.Result>() {

        override fun areItemsTheSame(
            oldItem: NowPlayingResponse.Result,
            newItem: NowPlayingResponse.Result
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: NowPlayingResponse.Result,
            newItem: NowPlayingResponse.Result
        ): Boolean {
            return oldItem == newItem
        }
    }

}
