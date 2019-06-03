package com.example.pmsystem.util.bottomnavigationdrawer

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pmsystem.project.CreateProject.CreateProjectFragment
import com.example.pmsystem.project.Home.HomeFragment
import com.example.pmsystem.R

class BottomNavFragment : Fragment() {

    lateinit var bottomNavClickListener: BottomNavClickListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_nav, container, false)

        val navView: BottomNavigationView = view!!.findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        return view
    }

    companion object {
        fun newInstance(): BottomNavFragment {
            val bottomNavFragment =
                BottomNavFragment()
            return bottomNavFragment
        }
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // instantiate a fragment
                    val homeFragment = HomeFragment.newInstance()
                    // send the fragment as argument to MainActivity and replace the fragment container with this fragment
                    bottomNavClickListener.onBottonNavClicked(homeFragment)

                    // this line is required so that the animation works on selected
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_create_project -> {
                    val createProjectFragment =
                        CreateProjectFragment.newInstance()
                    bottomNavClickListener.onBottonNavClicked(createProjectFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_something -> {
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BottomNavClickListener) {
            // set bottom nav click listener to this fragment
            bottomNavClickListener = context
        } else {
            throw ClassCastException(context.toString() + getString(R.string.bottom_nav_exception_message))
        }
    }

}