package com.example.pmsystem

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.pmsystem.manager.task.TaskListFragment
import com.example.pmsystem.util.bottomnavigationdrawer.BottomNavClickListener
import com.example.pmsystem.util.bottomnavigationdrawer.BottomNavFragment
import org.jetbrains.anko.toast
import com.example.pmsystem.R.id.message as message

class MainActivity : AppCompatActivity(), BottomNavClickListener {

    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isUserLoggedIn()

    }

    // this function takes in a new fragment and do the fragment transaction
    // main purpose of this is to reduce the amount of boiler plate code while doing fragment transaction
    private fun fragmentReplaceHandler(fragment: Fragment) {
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit()
    }

    private fun fragmentAddHandler(fragment: Fragment){
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().add(R.id.fragment_bottom_nav, fragment).commit()
    }

    private fun fragmentRemoveHandler(fragment: Fragment){
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().remove(fragment).commit()
    }

    // check if user is already logged in
    private fun isUserLoggedIn() {
        //TODO: check shared preferences (Bin)
        if(true){
            val taskListFragment : Fragment = TaskListFragment()
            fragmentReplaceHandler(taskListFragment)

            // display bottom nav drawer if user is logged in
            val bottomNavFragment = BottomNavFragment.newInstance()
            fragmentAddHandler(bottomNavFragment)
        }else{
            fragmentManager = supportFragmentManager
            // remove bottom nav fragment if it is on the screen and user is not logged in
            val bottomNavFragment = fragmentManager.findFragmentById(R.id.fragment_bottom_nav)
            if(bottomNavFragment != null){
                fragmentRemoveHandler(bottomNavFragment)
            }else{
                toast("No bottom nav is removed")
            }
        }
    }

     override fun onBottonNavClicked(fragment: Fragment) {
        fragmentReplaceHandler(fragment)
    }
}
