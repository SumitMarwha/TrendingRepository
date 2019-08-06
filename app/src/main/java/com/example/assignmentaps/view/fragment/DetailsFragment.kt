package com.example.assignmentaps.view.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assignmentaps.R
import com.example.assignmentaps.base.fragments.BaseFragment
import com.example.assignmentaps.databinding.FragmentDetailsBinding
import com.example.assignmentaps.model.RepositoryDetails
import com.example.assignmentaps.view.delegate.DetailUrlObserver
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.transition.TransitionInflater
import com.squareup.picasso.Picasso

class DetailsFragment: BaseFragment(), DetailUrlObserver {

    lateinit var binding: FragmentDetailsBinding

    companion object {
        fun newInstance(args: Bundle?): DetailsFragment {
            var detailsFragment = DetailsFragment()
            detailsFragment.arguments = args
            return detailsFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        if(arguments!!.containsKey("repo")) {
            binding.repository = arguments!!.getParcelable<RepositoryDetails>("repo")
        }
        binding.observer = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.imageView.setTransitionName(binding.repository!!.name)
        }
    }

    override fun openDetails(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    override fun startTransition() {
        startPostponedEnterTransition()
    }

}