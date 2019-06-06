package com.example.pmsystem.authentication.login


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.pmsystem.R
import com.example.pmsystem.model.LoginResponse
import com.example.pmsystem.network.ApiInterface
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_login, container, false)
        // Inflate the layout for this fragment
        login_sign_in_btn.setOnClickListener {

                var email = login_email_tiet.text.toString()
                var password = login_password_tiet.text.toString()
                if (TextUtils.isEmpty(email)) run {
                    Toast.makeText(getActivity(), "please input your email!", Toast.LENGTH_SHORT).show()
                } else if (TextUtils.isEmpty(password)){
                    Toast.makeText(getActivity(), "please input your password!", Toast.LENGTH_SHORT).show()


                }else{
                    var apiInterface  = ApiInterface.getRetrofitInstance().login(email,password)
                    apiInterface.enqueue(object : retrofit2.Callback<LoginResponse>{
                        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                            Log.e("on response", response.body()!!.userfirstname)
                            var sharedPreferences : SharedPreferences = activity!!.getSharedPreferences("userPref",Context.MODE_PRIVATE)
                            sharedPreferences.edit().putString("userid",response.body()!!.userid)
                            sharedPreferences.edit().putString("userfirstname",response.body()!!.userfirstname)
                            sharedPreferences.edit().putString("userlastname",response.body()!!.userlastname)
                            sharedPreferences.edit().putString("useremail",response.body()!!.useremail)
                            sharedPreferences.edit().putString("appapikey",response.body()!!.appapikey)
                            sharedPreferences.apply { sharedPreferences }

                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Log.e("on error", t.message)
                        }
                    })

            }
        }
        return view
    }


}
