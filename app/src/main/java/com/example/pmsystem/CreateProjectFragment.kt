package com.example.pmsystem


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CreateProjectFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_project, container, false)
    }

    // Call this function to create a new fragment
    companion object{
        fun newInstance(): CreateProjectFragment{
            val createProjectFragment = CreateProjectFragment()
            return createProjectFragment
        }
    }
}
