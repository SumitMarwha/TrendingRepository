package com.example.assignmentaps.view.fragment

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.assignmentaps.R
import com.example.assignmentaps.base.fragments.BaseFragment
import com.example.assignmentaps.databinding.FragmentMainBinding
import com.example.assignmentaps.model.RepositoryDetails
import com.example.assignmentaps.networking.Response
import com.example.assignmentaps.networking.Status
import com.example.assignmentaps.view.adapter.TrendingRepositoryAdapter
import com.example.assignmentaps.view.delegate.BaseObserver
import com.example.assignmentaps.view.viewmodel.MainActivityViewModel
import java.text.FieldPosition
import javax.inject.Inject

class MainFragment: BaseFragment(), BaseObserver {

    @Inject
    lateinit var viewModel: MainActivityViewModel

    lateinit var binding: FragmentMainBinding
    lateinit var adapter: TrendingRepositoryAdapter

    companion object {
        fun newInstance(args: Bundle?): MainFragment {
            var mainFragment = MainFragment()
            mainFragment.arguments = args
            return mainFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createAdapter()
        observeData()
    }

    fun createAdapter() {
        adapter = TrendingRepositoryAdapter(viewModel.getRepository().value!!.data!!, this)
        binding.myAdapter = adapter
    }

    fun observeData() {
        viewModel.getRepository().observe(viewLifecycleOwner, object: Observer<Response<ArrayList<RepositoryDetails>>> {
            override fun onChanged(t: Response<ArrayList<RepositoryDetails>>?) {
                processResponse(t)
            }
        })
    }

    fun processResponse(response: Response<ArrayList<RepositoryDetails>>?) {
        when(response!!.status) {
            Status.SUCCESS -> {
                binding.progressBar.setVisibility(View.GONE)
                binding.somethingWentWrong.setVisibility(View.GONE)
                adapter.notifyDataSetChanged()
            }
            Status.LOADING -> {
                binding.progressBar.setVisibility(View.VISIBLE)
                binding.somethingWentWrong.setVisibility(View.GONE)
            }
            Status.ERROR -> {
                binding.progressBar.setVisibility(View.GONE)
                binding.somethingWentWrong.setVisibility(View.VISIBLE)
            }
        }
    }

    override fun openDetails(repository: RepositoryDetails, imageView: ImageView) {
        var bundle = Bundle()
        bundle.putParcelable("repo", repository)
        openFragment(DetailsFragment.newInstance(bundle), imageView)
    }
}