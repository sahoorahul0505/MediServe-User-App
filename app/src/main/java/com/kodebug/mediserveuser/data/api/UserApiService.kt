package com.kodebug.mediserveuser.data.api


import com.kodebug.mediserveuser.data.model.userModel.CreateUserModel
import com.kodebug.mediserveuser.data.model.userModel.LoginResponse
import com.kodebug.mediserveuser.data.model.userModel.SpecificUserResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserApiService {

    @FormUrlEncoded
    @POST("createUser")
    suspend fun createUser(
        @Field("name") name: String,
        @Field("phone_number") phoneNumber: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("address") address: String,
        @Field("pinCode") pinCode: String
    ): Response<CreateUserModel>


    @FormUrlEncoded
    @POST("getSpecificUserData")
    suspend fun getSpecificUser(
        @Field("user_id") userId : String
    ) : Response<SpecificUserResponse>


    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email : String,
        @Field("password") password : String
    ) : Response<LoginResponse>

}