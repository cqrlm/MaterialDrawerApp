package com.example.materialdrawerapp.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdrawerapp.R

class PictureAdapter(private val pictures: List<Picture>) :
    RecyclerView.Adapter<PictureAdapter.PictureViewHolder>() {
    inner class PictureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val picture: ImageView = itemView.findViewById(R.id.picture)
        val title: TextView = itemView.findViewById(R.id.tv_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.gallery_cardview, parent, false)
        return PictureViewHolder(view)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        val picture = pictures[position]
        holder.title.text = picture.title
        holder.picture.setImageResource(picture.image)
    }

    override fun getItemCount(): Int {
        return pictures.size
    }

}
