package com.example.loginpage

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class UserProfileActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userprofile_layout)

//        获取Intent中的用户名信息
//        val userName = intent.getStringExtra("userName")

        // 在TextView控件中显示用户名信息
        val userProfileTextView = findViewById<TextView>(R.id.textView)
        userProfileTextView.text = "欢迎"
    }


}
