package com.example.loginpage

data class BaseInfoRequest(
    val key: String,
    val value: String,
    val description: String,
    val type: String,
    val enabled: Boolean
    )