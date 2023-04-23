package com.example.loginpage

data class LoginResponse(
    val resultCode: String?,
    val message: String?,
    val data: Data?
    ) {

    data class Data(
        val auth: Auth?,
        val isNeedUpdateMobile: String?
        )

    data class Auth(
        val idpUserId: String?,
        val accessToken: String?,
        val refreshToken: String?,
        val newUser: String?,
        val processId: String?
        )

}