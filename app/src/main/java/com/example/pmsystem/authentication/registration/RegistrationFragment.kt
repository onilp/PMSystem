package com.example.pmsystem.authentication.registration


import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import com.example.pmsystem.R
import com.example.pmsystem.authentication.login.LoginFragment
import com.example.pmsystem.model.RegistrationResponse
import com.example.pmsystem.network.ApiInterface
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
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
class RegistrationFragment : Fragment(), RegistrationContract.View {


    lateinit var registrationPresenter: RegistrationPresenter

    lateinit var loginFragment: LoginFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        var view: View = inflater.inflate(R.layout.fragment_register, container, false)

        loginFragment = LoginFragment.newInstance()


        registrationPresenter = RegistrationPresenter(this,requireContext())


        var textviewhaveAccount: TextView = view.findViewById(R.id.register_login_here_tv)
        textviewhaveAccount.setOnClickListener {
            registrationPresenter.onLoginHereClick()

        }


        var buttonSignUp : Button = view.findViewById(R.id.register_sign_up_btn)
        buttonSignUp.setOnClickListener{


            registrationPresenter.validateRegisterInputs(register_first_name_til,
                register_last_name_til,register_email_til,register_mobile_til,
                register_password_til,register_companysize_til,register_role_til)







//            var firstname = register_first_name_tiet.text.toString()
//            var lastname = register_last_name_tiet.text.toString()
//            var email = register_email_tiet.text.toString()
//            var mobile = register_mobile_tiet.text.toString()
//            var password = register_password_tiet.text.toString()
//            var companysize = register_companysize_tiet.text.toString()
//            var yourrole = register_role_tiet.text.toString()
//
//            if (TextUtils.isEmpty(firstname)) run {
//                Toast.makeText(getActivity(),  "Its toast!", Toast.LENGTH_SHORT).show()
//            } else if (TextUtils.isEmpty(lastname)) run {
//                Toast.makeText(getActivity(), "Its toast!", Toast.LENGTH_SHORT).show()
//            } else if (TextUtils.isEmpty(email)) run {
//                Toast.makeText(getActivity(), "Its toast!", Toast.LENGTH_SHORT).show()
//            } else if (TextUtils.isEmpty(mobile)) run {
//                Toast.makeText(getActivity(),  "Its toast!", Toast.LENGTH_SHORT).show()
//            } else if (TextUtils.isEmpty(password)) run {
//                Toast.makeText(getActivity(), "Its toast!", Toast.LENGTH_SHORT).show()
//            } else if (TextUtils.isEmpty(companysize)) run {
//                Toast.makeText(getActivity(), "Its toast!", Toast.LENGTH_SHORT).show()
//            } else if (TextUtils.isEmpty(yourrole)) run {
//                Toast.makeText(getActivity(), "Its toast!", Toast.LENGTH_SHORT).show()
//            }else{
//                var apiInterface = ApiInterface.getRetrofitInstance().registerUser(firstname,lastname,email,mobile,password,companysize,yourrole)
//
//                apiInterface.enqueue(object : retrofit2.Callback<RegistrationResponse>{
//                    override fun onResponse(call: Call<RegistrationResponse>, response: Response<RegistrationResponse>) {
//                        Log.e("on response", response.body()!!.toString())
//                    }
//
//                    override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
//                        Log.e("on error", t.message)
//                    }
//                })
//
//            }



        }

        return view
    }



    override fun displayInputError(textInputLayout: TextInputLayout, errorMsg: String) {

        textInputLayout.isErrorEnabled = true
        textInputLayout.error = errorMsg
    }

    override fun removeInputError(textInputLayout: TextInputLayout) {
        textInputLayout.isErrorEnabled = false

    }

    override fun navigateToLogin(msg: String) {
        fragmentManager!!.beginTransaction().replace(R.id.fragment_container, LoginFragment())
            .commit()


    }

    companion object {
        fun newInstance(): RegistrationFragment {
            return RegistrationFragment()
        }
    }



}
