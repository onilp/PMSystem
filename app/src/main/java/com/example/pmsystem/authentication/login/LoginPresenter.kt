package com.example.pmsystem.authentication.login

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.SharedPreferences
import android.support.design.widget.TextInputLayout
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.pmsystem.MyApplication
import com.example.pmsystem.model.LoginResponse
import com.example.pmsystem.network.ApiInterface
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class LoginPresenter(var view: LoginContract.View, var context: Context) : LoginContract.Presenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

     var TAG : String = LoginPresenter::class.java.simpleName




    override fun loginButtonClicked(
        login_email_til: TextInputLayout,
        login_password_til: TextInputLayout
    ) {

        MyApplication.component.inject(this)

        var email = login_email_til.editText?.text.toString()
        var password = login_password_til.editText?.text.toString()
        if (TextUtils.isEmpty(email)) run {
            view.showInputError(login_password_til,"input the email")
        } else if (TextUtils.isEmpty(password)){
            Toast.makeText(context, "please input your password!", Toast.LENGTH_SHORT).show()


        }else{
            var apiInterface  = ApiInterface.getRetrofitInstance().login(email,password)
            apiInterface.enqueue(object : retrofit2.Callback<LoginResponse>{
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    //Log.e("on response", response.body()!!.userfirstname)
                    var sharedPreferences : SharedPreferences = context!!.getSharedPreferences("userPref",Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString("userid",response.body()!!.userid)
                    editor.putString("userfirstname",response.body()!!.userfirstname)
                    editor.putString("userlastname",response.body()!!.userlastname)
                    editor.putString("useremail",response.body()!!.useremail)
                    editor.putString("appapikey",response.body()!!.appapikey)
                    editor.apply()

                    if(email.equals(response.body()!!.useremail)) {
                        view.loginSuccess("login success")
                        view.getTohomePage()
                    }else{
                        Toast.makeText(context, "invalid login", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("on error", t.message)
                    view.loginInFailed("login failed")
                }
            })

        }



    }
}