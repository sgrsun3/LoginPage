package com.example.loginpage

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
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
                    userProfileTextView.text = "欢迎：$name，你的性别是：$gender，生日是：$birthday，手机号是：$phone"
                }
            } else {
                userProfileTextView.text = "获取用户信息失败"
            }
        }
    }
}

