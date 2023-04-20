package com.example.loginpage

import android.content.Context
import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitUtil {
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
                Log.e("RetrofitUtil", "login() failed", t)
            }
        })
    }

    fun getUserProfile(context: Context, callback: (Response<BaseInfoResponse>) -> Unit) {

        val sharedPreferencesAccessToken = context.getSharedPreferences("accessToken_pref", Context.MODE_PRIVATE)
        val sharedPreferencesIdpUserId = context.getSharedPreferences("idpUserId_pref", Context.MODE_PRIVATE)

        // 读取SharedPreferences中的accessToken
        val accessToken = sharedPreferencesAccessToken.getString("accessToken", "")
        // 读取SharedPreferences中的idpUserId
        val idpUserId = sharedPreferencesIdpUserId.getString("idpUserId", "")


        // 发送API请求
        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.getUserProfile(
            accessToken = "Bearer $accessToken",
            idpUserId = idpUserId,
            buId = idpUserId,
        )

        call.enqueue(object : Callback<BaseInfoResponse> {
            override fun onResponse(call: Call<BaseInfoResponse>, response: Response<BaseInfoResponse>) {
                callback(response)
            }

            override fun onFailure(call: Call<BaseInfoResponse>, t: Throwable) {
                Log.e("RetrofitUtil", "getUserProfile() failed", t)
            }
        })
    }

}


