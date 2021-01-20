package com.jasbir.flix.View.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jasbir.flix.R

class GenricAdapter (var mlist : ArrayList<String>) :RecyclerView.Adapter<GenricAdapter.Viewholder>(){

    inner class Viewholder(itemView: View ): RecyclerView.ViewHolder(itemView){

        var item : TextView
        init {
            item = itemView.findViewById(R.id.genricText)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.genricitem,parent,false)

        return Viewholder(view)


    }

    override fun getItemCount(): Int {

       return mlist.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        holder.item.text = mlist.get(position)
    }
}