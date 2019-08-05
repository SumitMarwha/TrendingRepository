package com.example.assignmentaps.view.adapter

import android.databinding.DataBindingUtil
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.assignmentaps.BR
import com.example.assignmentaps.R
import com.example.assignmentaps.databinding.ItemRowBinding
import com.example.assignmentaps.model.RepositoryDetails
import com.example.assignmentaps.view.delegate.BaseObserver

open class TrendingRepositoryAdapter(var trendingUsers: ArrayList<RepositoryDetails>, var baseObserver: BaseObserver): RecyclerView.Adapter<TrendingRepositoryAdapter.TrendingRepositoryViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TrendingRepositoryViewHolder {
        val binding: ItemRowBinding = DataBindingUtil.inflate(LayoutInflater.from(p0.context),
            R.layout.item_row, p0, false)
        return TrendingRepositoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return trendingUsers.size
    }

    override fun onBindViewHolder(p0: TrendingRepositoryViewHolder, p1: Int) {
        var repository: RepositoryDetails = trendingUsers.get(p1)
        p0.bind(repository)
        ViewCompat.setTransitionName(p0.binding.imageView, repository.name)
        p0.binding.mainLayout.setOnClickListener {
            baseObserver.openDetails(repository, p0.binding.imageView)
        }
    }

    class TrendingRepositoryViewHolder(var binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(obj: RepositoryDetails) {
            binding.setVariable(BR.repository, obj)
            binding.executePendingBindings()
        }
    }
}