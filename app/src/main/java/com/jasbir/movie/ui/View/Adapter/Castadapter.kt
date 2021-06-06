package com.jasbir.movie.ui.View.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jasbir.movie.data.dataclass.CastResponse
import com.jasbir.movie.R

class Castadapter (var mlist : List<CastResponse.CastX>): RecyclerView.Adapter<Castadapter.Viewholder>() {


    inner class  Viewholder(itemView : View) :RecyclerView.ViewHolder(itemView){

        var name : TextView
        var pic : ImageView
        init {
            name = itemView.findViewById(R.id.castName)
            pic = itemView.findViewById(R.id.castPic)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.castitem,parent,false)
        return  Viewholder(view)
    }

    override fun getItemCount(): Int {
        return  mlist.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        var image = mlist.get(position).profile_path
        Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/w500/$image")
            .into(holder.pic)
        holder.name.text = mlist.get(position).name

    }
}