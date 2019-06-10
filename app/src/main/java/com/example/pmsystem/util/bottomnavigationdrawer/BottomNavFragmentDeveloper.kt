package com.example.pmsystem.util.bottomnavigationdrawer

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pmsystem.MainActivity
import com.example.pmsystem.MyApplication
import com.example.pmsystem.manager.createproject.CreateProjectFragment
import com.example.pmsystem.manager.home.HomeFragment
import com.example.pmsystem.R
import com.example.pmsystem.authentication.login.LoginFragment
import com.example.pmsystem.developer.showtasklist.ShowTaskListFragment
import javax.inject.Inject
import com.example.pmsystem.manager.assign.AssignFragment

class BottomNavFragmentDeveloper : Fragment() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    lateinit var bottomNavClickListener: BottomNavClickListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_nav_developer, container, false)

        MyApplication.component.inject(this)

        val navView: BottomNavigationView = view!!.findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        return view
    }

    companion object {
        fun newInstance(): BottomNavFragmentDeveloper {
            return BottomNavFragmentDeveloper()
        }
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // instantiate a fragment
                    val showTaskListFragment: Fragment = ShowTaskListFragment()
                    // send the fragment as argument to MainActivity and replace the fragment container with this fragment
                    bottomNavClickListener.onBottonNavClicked(showTaskListFragment)

                    // this line is required so that the animation works on selected
                    return@OnNavigationItemSelectedListener false
                }
                R.id.nav_signout -> {
                    sharedPreferences.edit().clear().apply()
                    val mainActivityIntent = Intent(context, MainActivity::class.java)
                    startActivity(mainActivityIntent)
                    return@OnNavigationItemSelectedListener false
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