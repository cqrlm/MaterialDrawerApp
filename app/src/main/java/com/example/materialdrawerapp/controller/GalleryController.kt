package com.example.materialdrawerapp.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluelinelabs.conductor.Controller
import com.example.materialdrawerapp.R
import com.example.materialdrawerapp.recyclerview.Picture
import com.example.materialdrawerapp.recyclerview.PictureAdapter
import kotlinx.android.synthetic.main.gallery_fragment.view.*

class GalleryController : Controller() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.gallery_fragment, container, false)
        val recyclerView = view.recycler_view
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = PictureAdapter(generatePictures())
        (activity as AppCompatActivity).supportActionBar?.title = resources?.getString(R.string.gallery_title)
        return view
    }

    private fun generatePictures(): List<Picture> {
        return listOf(
            Picture(R.drawable.honka, "Honka"),
            Picture(R.drawable.pepe, "Pepe"),
            Picture(R.drawable.roflan, "Roflan"),
            Picture(R.drawable.cat, "Cat")
        )
    }
}
