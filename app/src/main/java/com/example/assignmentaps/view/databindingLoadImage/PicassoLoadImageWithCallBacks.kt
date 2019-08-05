package com.example.assignmentaps.view.databindingLoadImage

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.example.assignmentaps.view.delegate.DetailUrlObserver
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class PicassoLoadImageWithCallBacks {

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["image", "observer"])
        fun setImageUrl(view: ImageView, imageUrl: String, detailUrlObserver: DetailUrlObserver) {
            Picasso.with(view.context).load(imageUrl)
                .noFade().into(view, object : Callback {
                override fun onSuccess() {
                    detailUrlObserver.startTransition()
                }

                override fun onError() {
                    detailUrlObserver.startTransition()
                }
            })
        }
    }
}