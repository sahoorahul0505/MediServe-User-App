package com.kodebug.mediserveuser.data.model.userModel

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val message: String,
    val status: Int,
    @SerializedName("user_id")
    val userId: String
)