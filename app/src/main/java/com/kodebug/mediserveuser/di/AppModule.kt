package com.kodebug.mediserveuser.di

import android.content.Context
import com.kodebug.mediserveuser.constants.Constants.BASE_URL
import com.kodebug.mediserveuser.data.api.UserApiService
import com.kodebug.mediserveuser.data.datastore.DataStoreManager
import com.kodebug.mediserveuser.data.repository.userRepo.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    @Singleton
    fun providesRetrofit() : Retrofit{
        return Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit) : UserApiService {
        return retrofit.create(UserApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesUserRepository(userApiService: UserApiService) : UserRepository {
        return UserRepository(userApiService)
    }


    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context : Context): DataStoreManager {
        return DataStoreManager(context)
    }
}