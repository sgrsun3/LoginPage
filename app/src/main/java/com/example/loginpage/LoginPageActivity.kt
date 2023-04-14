package com.example.loginpage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginPageActivity : Activity() {

    private lateinit var retrofitUnit: RetrofitUnit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginpage_layout)

        retrofitUnit = RetrofitUnit

        //定义组件
        val mobileEditText = findViewById<EditText>(R.id.editTextPhone)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword)
        val loginButton = findViewById<Button>(R.id.button)

        //登陆按钮的监听器
        loginButton.setOnClickListener {
            val mobile = mobileEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (mobile.isNotEmpty() && password.isNotEmpty()) {
                val loginRequest = LoginRequest(mobile, password)
                Log.i("TEST","phone :$mobile")

                retrofitUnit.login(loginRequest) { response ->
                    if (response.isSuccessful && response.body() != null && response.body()?.data != null) {
                        //获取LoginResponse里的数据
                        val auth = response.body()?.data?.auth
                        val accessToken = auth?.accessToken
                        val permToken = auth?.refreshToken

                        val accesstokenPref = getSharedPreferences("accessToken_pref", Context.MODE_PRIVATE)
                        val permtokenPref = getSharedPreferences("permToken_pref", Context.MODE_PRIVATE)

                        if (accessToken != null) {
                            // 登录成功
                            Toast.makeText(this, "$mobile 登录成功", Toast.LENGTH_SHORT).show()

                            //SharedPreference存储accessToken和permToken
                            with(accesstokenPref.edit()) {
                                putString("accessToken_pref", accessToken)
                                apply()
                            }

                            with(permtokenPref.edit()) {
                                putString("permToken_pref", permToken)
                                apply()
                            }
                            Toast.makeText(this, "Access & Perm Token 已经储存", Toast.LENGTH_SHORT).show()
                            // 读取SharedPreferences中的accessToken
                            //val accessToken1 = sharedPreferences.getString("accessToken_pref", "")

                            //跳转到用户信息页
                            val intent = Intent(this, UserProfileActivity::class.java)
                            startActivity(intent)


                        } else {
                            // 登录失败
                            Toast.makeText(this, "$mobile 登录失败", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // 请求失败
                        Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show()
                        Log.e("LoginPageActivity", "login() failed: ${response.errorBody()}")
                    }
                }
            } else {
                Toast.makeText(this, "手机号码或密码不能为空", Toast.LENGTH_SHORT).show()
            }
        }


    }


    }