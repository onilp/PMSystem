package com.example.pmsystem.model

data class LoginResponse(
    val appapikey: String,
    val msg: List<String>,
    val useremail: String,
    val userfirstname: String,
    val userid: String,
    val userlastname: String,
    val userrole: String
)