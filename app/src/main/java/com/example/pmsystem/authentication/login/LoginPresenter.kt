package com.example.pmsystem.authentication.login

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.SharedPreferences
import android.support.design.widget.TextInputLayout
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.pmsystem.model.LoginResponse
import com.example.pmsystem.network.ApiInterface
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Response

class LoginPresenter(var view: LoginContract.View, var context: Context) : LoginContract.Presenter {

     var TAG : String = LoginPresenter::class.java.simpleName




    override fun loginButtonClicked(
        login_email_til: TextInputLayout,
        login_password_til: TextInputLayout
    ) {


        var email = login_email_til.editText.toString()
        var password = login_password_til.editText.toString()
        if (TextUtils.isEmpty(email)) run {
            view.showInputError(login_password_til,"input the ")
        } else if (TextUtils.isEmpty(password)){
            Toast.makeText(context, "please input your password!", Toast.LENGTH_SHORT).show()


        }else{
            var apiInterface  = ApiInterface.getRetrofitInstance().login(email,password)
            apiInterface.enqueue(object : retrofit2.Callback<LoginResponse>{
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    Log.e("on response", response.body()!!.userfirstname)
                    var sharedPreferences : SharedPreferences = context!!.getSharedPreferences("userPref",Context.MODE_PRIVATE)
                    sharedPreferences.edit().putString("userid",response.body()!!.userid)
                    sharedPreferences.edit().putString("userfirstname",response.body()!!.userfirstname)
                    sharedPreferences.edit().putString("userlastname",response.body()!!.userlastname)
                    sharedPreferences.edit().putString("useremail",response.body()!!.useremail)
                    sharedPreferences.edit().putString("appapikey",response.body()!!.appapikey)
                    sharedPreferences.apply { sharedPreferences }
                    view.loginSuccess("login success")
                    view.getTohomePage()

                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("on error", t.message)
                    view.loginInFailed("login failed")
                }
            })

        }



    }
}