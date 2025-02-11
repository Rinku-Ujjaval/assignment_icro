package com.example.assignment_icro_rinku.network

import com.example.assignment_icro_rinku.model.AppSafety
import retrofit2.http.GET

interface Services {

    @GET("10/")
    suspend fun appSafety(): AppSafety

}