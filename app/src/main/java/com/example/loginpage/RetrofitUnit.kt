package com.example.loginpage

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitUnit {
    private val url = "https://app.sgmlink.com:443"

    private val okHttpClient = OkHttpClient()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    fun login(loginRequest: LoginRequest, callback: (Response<LoginResponse>) -> Unit) {
        val call = apiService.login(loginRequest)
        call.enqueue(object:Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                callback(response)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("RetrofitUnit", "login() failed", t)
            }
        })
    }
}


