package com.example.materialdrawerapp.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bluelinelabs.conductor.Controller
import com.example.materialdrawerapp.R

class HomeController : Controller() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        val imageView = view.findViewById<ImageView>(R.id.home_image)
        imageView.setImageResource(R.drawable.home)
        (activity as AppCompatActivity).supportActionBar?.title = resources?.getString(R.string.home_title)
        return view
    }
}