package com.example.loginpage

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    // 登录接口
    @POST("/service/mycadillacv3/rest/api/public/auth/v3/authorize")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    // 获取用户信息接口
    @POST("/service/mycadillacv3/rest/api/private/userProfile/v4/baseInfo")
    fun getUserProfile(
        @Header("client_id") clientId: String = "a4tYzhY2KKfXlBLKmHCqJcVnQo2QfmEHG6ewWCBBTOayjFJrYi",
        @Header("client_secret") clientSecret: String = "Dmm09ZnjPPmfVxsbQn2FQCTG260BivEHBiyQLYTC3BX5HcR01RWeqSXx06K5",
        @Header("access_token") accessToken: String? = null,
        @Header("idpUserId") idpUserId: String? = null,
        @Header("buId") buId: String? = null,
    ): Call<BaseInfoResponse>

    //刷新Access Token接口
    @POST("/service/mycadillacv3/rest/api/public/auth/v3/refreshToken")
    fun refresh(@Body refreshTokenRequest: RefreshTokenRequest):Call<RefreshTokenResponse>

}