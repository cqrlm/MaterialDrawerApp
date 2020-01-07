package com.example.materialdrawerapp.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bluelinelabs.conductor.Controller
import com.example.materialdrawerapp.R

class SlideshowController : Controller() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.slideshow_fragment, container, false)
        val textView = view.findViewById<TextView>(R.id.tv_slideshow)
        (activity as AppCompatActivity).supportActionBar?.title = resources?.getString(R.string.slideshow_title)
        textView.text = resources?.getString(R.string.slideshow_text)
        return view
    }
}
