package com.example.pmsystem.authentication.login

import android.support.design.widget.TextInputLayout

interface LoginContract {

    interface View {
        fun showInputError(textInputLayout: TextInputLayout, error: String)

        fun getTohomePage()

        fun loginSuccess(msg: String)

        fun loginInFailed(msg: String)
    }

    interface Presenter {
        fun loginButtonClicked(
            login_mobile_til: TextInputLayout,
            login_password_til: TextInputLayout
        )
    }

}