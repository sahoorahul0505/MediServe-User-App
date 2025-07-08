package com.kodebug.mediserveuser.data.repository.userRepo

import com.kodebug.mediserveuser.data.api.UserApiService
import com.kodebug.mediserveuser.data.model.userModel.SpecificUserData
import javax.inject.Inject

class UserRepository @Inject constructor(private val userApiService: UserApiService) {

    suspend fun createUser(
        name: String,
        phoneNumber: String,
        email: String,
        password: String,
        address: String,
        pinCode: String
    ) : Result<String> {
        return try {
            val response = userApiService.createUser(name, phoneNumber, email, password, address, pinCode)
            if (response.isSuccessful && response.body()?.status == 200){
                Result.success(response.body()!!.message) // returns userId
            }else{
                Result.failure(Exception(response.body()?.message ?: "An error occurred")) // returns error message and will be handled in the ViewModel
            }
        }catch (e : Exception){
            Result.failure(e)
        }
    }

    suspend fun isUserApproved(userId : String) : Result<Boolean>{
        return try {
            val response = userApiService.getSpecificUser(userId)
            if (response.isSuccessful && response.body()?.status == 200){
                val approved = response.body()!!.specificUserData.isApproved == 1
                Result.success(approved)
            }else{
                Result.failure(Exception(response.body()?.message ?: "Invalid user data received"))
            }
        }catch (e : Exception){
            Result.failure(e)
        }
    }

    suspend fun loginUser(email : String, password : String) : Result<String> {
        return try {
            val response = userApiService.loginUser(email, password)
            if (response.isSuccessful && response.body()?.status == 200){
                Result.success(response.body()?.userId.toString())
//            }else if (response.body()?.status == 400){
//                Result.failure(Exception(response.body()?.message ?: "Invalid email or password"))
            } else{
                Result.failure(Exception(response.body()?.message ?: "An error occurred"))
            }
        }catch (e : Exception){
            Result.failure(e)
        }
    }

    suspend fun getSpecificUser(userId : String) : Result<SpecificUserData> {
        return try {
            val response = userApiService.getSpecificUser(userId)
            if (response.isSuccessful && response.body()?.status == 200){
                val data = response.body()!!.specificUserData
                Result.success(data)
            }else{
                Result.failure(Exception(response.body()?.message ?: "Failed to fetch user data"))
            }
        }catch (e : Exception){
            Result.failure(e)
        }
    }

    suspend fun updateUser(
        userId : String,
        name : String,
        phoneNumber : String,
        email : String,
        address : String,
        pinCode : String,
        password : String
    ) : Result<String> {
        return try {
            val response = userApiService.updateUser(userId, name, phoneNumber, email, address, pinCode, password)
            if (response.isSuccessful && response.body()?.status == 200){
                Result.success(response.body()!!.message)
            }else{
                Result.failure(Exception(response.body()?.message ?: "An error occurred"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }
}