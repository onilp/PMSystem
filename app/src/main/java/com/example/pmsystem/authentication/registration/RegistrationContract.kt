package com.example.pmsystem.authentication.registration

import android.support.design.widget.TextInputLayout

interface RegistrationContract {


    interface View {
        fun displayInputError(textInputLayout: TextInputLayout, errorMsg: String)

        fun removeInputError(textInputLayout: TextInputLayout)

        fun navigateToLogin(msg: String)
    }


    interface Presenter {

        fun validateSingleRegisterInput(textInputLayout: TextInputLayout, nameOfInput: String)

        fun validateRegisterInputs(
            registerFirstName: TextInputLayout,
            registerLastName: TextInputLayout,
            registeremail: TextInputLayout,
            registermobile: TextInputLayout,
            registerpassword: TextInputLayout,
            registercompanysize: TextInputLayout,
            registeryourrole: TextInputLayout
        )

        fun onLoginHereClick()
    }


}