package com.example.loginpage

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class UserProfileActivity: Activity() {

    private lateinit var retrofitUnit: RetrofitUtil

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.userprofile_layout)

        retrofitUnit = RetrofitUtil
        // 在TextView控件中显示用户名信息
        val userProfileTextView = findViewById<TextView>(R.id.textView)
        // 调用getUserProfile()方法获取用户信息
        retrofitUnit.getUserProfile(this) { response ->

            if (response.isSuccessful) {

                val data = response.body()?.data
                val profileInfo = data?.profileInfo
                val garage = data?.garage

                if (profileInfo != null && garage != null) {

                    val name = profileInfo.name
                    val gender = profileInfo.gender
                    val birthday = profileInfo.birthday
                    val phone = profileInfo.phone
                    //val nickname = garage.nickName 昵称：$nickname,

                    // 将获取到的用户信息展示在TextView中
                    userProfileTextView.text = "欢迎登陆：$name，\n你的性别是：$gender，\n生日是：$birthday，\n手机号是：$phone"

                }

            } else {

                userProfileTextView.text = "获取用户信息失败"

                if (response.code() == 401){

                    userProfileTextView.text = "Error Code 401, 获取用户信息失败"
                    Log.e("401","Error Code 401")

                    val sharedPreferencesUserProfile = getSharedPreferences("user_profile", Context.MODE_PRIVATE)
                    //读取SharedPreferences中的UserId
                    val userName = sharedPreferencesUserProfile.getString("idpuser_id", "")
                    //读取permToken
                    val permToken = sharedPreferencesUserProfile.getString("perm_token", "")
                    val refreshTokenRequest = RefreshTokenRequest("$userName", "$permToken")

                    retrofitUnit.getRefreshToken(refreshTokenRequest) { response ->

                        if (response.isSuccessful) {

                            val newAccessToken = response.body()?.data?.accessToken
                            val editor = sharedPreferencesUserProfile.edit()
                            editor.putString("access_token",newAccessToken)
                            editor.apply()

                            // 使用新的accessToken重新请求LoginRequest并更新SharedPreferences
                            val loginRequest = LoginRequest("$userName", "$newAccessToken")

                            retrofitUnit.login(loginRequest) { response ->

                                if (response.isSuccessful) {

                                    val newPermToken = response.body()?.data?.auth?.refreshToken
                                    val editor = sharedPreferencesUserProfile.edit()
                                    editor.putString("perm_token", newPermToken)
                                    editor.apply()

                                }

                            }

                        }

                    }

                }

            }

        }

    }

}

