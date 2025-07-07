package com.kodebug.mediserveuser.data.model.userModel

data class SpecificUserResponse(
    val message: String,
    val specificUserData: SpecificUserData,
    val status: Int
)