package com.example.pmsystem

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.pmsystem.authentication.login.LoginFragment
import com.example.pmsystem.authentication.registration.RegistrationFragment
import com.example.pmsystem.project.home.HomeFragment
import com.example.pmsystem.util.bottomnavigationdrawer.BottomNavClickListener
import com.example.pmsystem.util.bottomnavigationdrawer.BottomNavFragment
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import javax.inject.Inject
//import org.jetbrains.anko.toast
import com.example.pmsystem.R.id.message as message

class MainActivity : AppCompatActivity(), AnkoLogger,
    BottomNavClickListener {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    lateinit var fragmentManager: FragmentManager

    lateinit var loginFragment : LoginFragment

    lateinit var registrationFragment: RegistrationFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        isUserLoggedIn()

    }

    private fun init() {
        MyApplication.component.inject(this)
    }

    // this function takes in a new fragment and do the fragment transaction
    // main purpose of this is to reduce the amount of boiler plate code while doing fragment transaction
    private fun fragmentReplaceHandler(fragment: Fragment) {
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit()
    }

    private fun bottomNavAddHandler(fragment: Fragment){
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().add(R.id.fragment_bottom_nav, fragment).commit()
    }

    private fun fragmentRemoveHandler(fragment: Fragment){
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().remove(fragment).commit()
    }

    // check if user is already logged in
    private fun isUserLoggedIn() {
        if(sharedPreferences.getString("userid", "") != ""){
            val homeFragment: Fragment = HomeFragment.newInstance()
            fragmentReplaceHandler(homeFragment)

            // display bottom nav drawer if user is logged in
            val bottomNavFragment = BottomNavFragment.newInstance()
            bottomNavAddHandler(bottomNavFragment)
        }else{
            fragmentManager = supportFragmentManager
            // remove bottom nav fragment if it is on the screen and user is not logged in
            val bottomNavFragment: Fragment? = fragmentManager.findFragmentById(R.id.fragment_bottom_nav)
            if(bottomNavFragment != null){
                fragmentRemoveHandler(bottomNavFragment)
            }

            val loginFragment: Fragment = LoginFragment()
            fragmentReplaceHandler(loginFragment)
        }
    }

    override fun onBottonNavClicked(fragment: Fragment) {
        fragmentReplaceHandler(fragment)
    }

}
