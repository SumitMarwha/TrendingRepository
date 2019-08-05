package com.example.assignmentaps.view.activity

import android.os.Bundle
import com.example.assignmentaps.R
import com.example.assignmentaps.base.activities.BaseActivity
import com.example.assignmentaps.view.fragment.MainFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generic_skeleton)
        setContainer(R.id.container_fragment)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        addFragment(MainFragment.newInstance(null))
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
