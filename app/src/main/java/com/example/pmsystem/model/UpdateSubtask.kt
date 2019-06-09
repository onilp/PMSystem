package com.example.pmsystem.model

import com.google.gson.annotations.SerializedName

data class UpdateSubtask (@SerializedName("msg") var msg : List<String> = ArrayList<String>()) {
}