package com.kodebug.mediserveuser.data.model.userModel

import com.google.gson.annotations.SerializedName

data class SpecificUserData(
    val address: String,
    val blocked: Int,
    @SerializedName("created_at")
    val createdAt: String,
    val email: String,
    @SerializedName("is_approved")
    val isApproved: Int,
    val name: String,
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    val pinCode: String,
    @SerializedName("user_id")
    val userId: String
)