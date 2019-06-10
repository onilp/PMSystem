package com.example.pmsystem.authentication.login


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.pmsystem.MainActivity

import com.example.pmsystem.R
import com.example.pmsystem.authentication.registration.RegistrationFragment
import com.example.pmsystem.model.LoginResponse
import com.example.pmsystem.network.ApiInterface
import com.example.pmsystem.manager.createproject.CreateProjectFragment
import com.example.pmsystem.manager.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
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
class LoginFragment : Fragment(),LoginContract.View {

    lateinit var loginPresenter: LoginPresenter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_login, container, false)
        // Inflate the layout for this fragment

        loginPresenter = LoginPresenter(this,requireContext())


        var button : Button = view.findViewById(R.id.login_create_account_btn)
        button.setOnClickListener {
            fragmentManager!!.beginTransaction().replace(R.id.fragment_container,  RegistrationFragment()).addToBackStack(null).commit();
        }


        var button2: Button =  view.findViewById(R.id.login_sign_in_btn)
        button2.setOnClickListener {
            loginPresenter.loginButtonClicked(login_email_til, login_password_til)

        }
        return view
    }


    override fun showInputError(textInputLayout: TextInputLayout, error: String) {
        textInputLayout.isErrorEnabled = true
        textInputLayout.error = error
    }

    override fun getTohomePage() {
//        fragmentManager!!.beginTransaction().replace(R.id.fragment_container, HomeFragment())
//            .addToBackStack(null).commit()
        val mainActivityIntent: Intent = Intent(context, MainActivity::class.java)
        startActivity(mainActivityIntent)

    }

    override fun loginSuccess(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

    }

    override fun loginInFailed(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

    }


    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

}


