package com.example.loginpage

data class AccessTokenResponse(
    val resultCode: String,
    val message: String,
    val data: AccessTokenData
)

data class AccessTokenData(
    val userName: String,
    val expiresInSeconds: String,
    val accessToken: String
)