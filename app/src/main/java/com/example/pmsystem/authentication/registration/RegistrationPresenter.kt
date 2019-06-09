package com.example.pmsystem.authentication.registration

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.pmsystem.authentication.login.LoginContract
import com.example.pmsystem.model.RegistrationResponse
import com.example.pmsystem.network.ApiInterface
import kotlinx.android.synthetic.main.fragment_register.*
import retrofit2.Call
import retrofit2.Response

class RegistrationPresenter(var view: RegistrationContract.View, var context: Context): RegistrationContract.Presenter {



    override fun validateSingleRegisterInput(
        textInputLayout: TextInputLayout,
        nameOfInput: String
    ) {

        if (TextUtils.isEmpty(textInputLayout.editText!!.text)) {
            view.displayInputError(textInputLayout, "Please enter +$nameOfInput")
        } else {
            view.removeInputError(textInputLayout)
        }

    }

    override fun validateRegisterInputs(
        registerFirstName: TextInputLayout,
        registerLastName: TextInputLayout,
        registeremail: TextInputLayout,
        registermobile: TextInputLayout,
        registerpassword: TextInputLayout,
        registercompanysize: TextInputLayout,
        registeryourrole: TextInputLayout
    ) {


        var firstname : String = registerFirstName.editText.toString()
        var lastname : String= registerLastName.editText.toString()
        var email: String = registeremail.editText.toString()
        var mobile : String= registermobile.editText.toString()
        var password: String = registerpassword.editText.toString()
        var companysize : String= registercompanysize.editText.toString()
        var yourrole: String = registeryourrole.editText.toString()

        if (TextUtils.isEmpty(firstname)) run {
            view.displayInputError(registerFirstName,"input the firstname ")
        } else if (TextUtils.isEmpty(lastname)) run {
            view.displayInputError(registerLastName,"input the lastname ")
        } else if (TextUtils.isEmpty(email)) run {
            view.displayInputError(registeremail,"input the email ")
        } else if (TextUtils.isEmpty(mobile)) run {
            view.displayInputError(registermobile,"input the mobile ")
        } else if (TextUtils.isEmpty(password)) run {
            view.displayInputError(registerpassword,"input the password ")
        } else if (TextUtils.isEmpty(companysize)) run {
            view.displayInputError(registercompanysize,"input the companysize ")
        } else if (TextUtils.isEmpty(yourrole)) run {
            view.displayInputError(registeryourrole,"input the yourrole ")
        }else{
            var apiInterface = ApiInterface.getRetrofitInstance().registerUser(firstname,lastname,email,mobile,password,companysize,yourrole)

            apiInterface.enqueue(object : retrofit2.Callback<RegistrationResponse>{
                override fun onResponse(call: Call<RegistrationResponse>, response: Response<RegistrationResponse>) {
                    Log.e("on response", response.body()!!.toString())
                }

                override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                    Log.e("on error", t.message)
                }
            })


        }
    }

    override fun onLoginHereClick() {

        view.navigateToLogin("")

    }


}