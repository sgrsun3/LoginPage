package com.example.loginpage

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/service/mycadillacv3/rest/api/public/auth/v3/authorize")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}