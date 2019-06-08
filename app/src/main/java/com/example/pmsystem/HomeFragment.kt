package com.example.pmsystem


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pmsystem.manager.assign.AssignFragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, AssignFragment())?.commit()

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object{
        fun newInstance(): HomeFragment{
            return HomeFragment()
        }
    }

}
