package com.example.assignmentaps.view.databindingLoadImage

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

class PicassoLoadImage {

    companion object {
        @JvmStatic
        @BindingAdapter("imageResource")
        fun setImageUrl(view: ImageView, imageUrl: String) {
            Picasso.with(view.context).load(imageUrl).into(view)
        }
    }
}