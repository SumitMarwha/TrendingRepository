package com.example.assignmentaps.view.delegate

import android.widget.ImageView
import com.example.assignmentaps.model.RepositoryDetails

interface BaseObserver {

    fun openDetails(respository: RepositoryDetails, imageView: ImageView)

}