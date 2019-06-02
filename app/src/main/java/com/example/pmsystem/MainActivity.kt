package com.example.pmsystem

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.widget.TextView
import android.widget.Toast
import com.example.pmsystem.R.id.home
import com.example.pmsystem.R.id.nav_view
import org.jetbrains.anko.toast
import com.example.pmsystem.R.id.message as message

class MainActivity : AppCompatActivity() {

    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        isUserLoggedIn()

    }

    // this function takes in a new fragment and do the fragment transaction
    // main purpose of this is to reduce the amount of boiler plate code while doing fragment transaction
    private fun fragmentTransactionHandler(fragment: Fragment) {
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit()
    }

    private val onNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
                val homeFragment = HomeFragment.newInstance()
                fragmentTransactionHandler(homeFragment)
                // this line is required so that the animation works on selected
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_create_project -> {
                val createProjectFragment = CreateProjectFragment.newInstance()
                fragmentTransactionHandler(createProjectFragment)

                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_something -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun isUserLoggedIn() {
        if(true){
            val homeFragment = HomeFragment.newInstance()
            fragmentTransactionHandler(homeFragment)
        }else{
            //login fragment


            // this code is needed to remove the bottom navigation drawer in login fragment

        }
    }
}
