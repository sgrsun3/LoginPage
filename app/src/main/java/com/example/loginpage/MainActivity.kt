package com.example.loginpage

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : Activity() {

    private lateinit var retrofitUnit: RetrofitUnit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_1)

        retrofitUnit = RetrofitUnit

        val mobileEditText = findViewById<EditText>(R.id.editTextPhone)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword)
        val loginButton = findViewById<Button>(R.id.button)

        loginButton.setOnClickListener {
            val mobile = mobileEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (mobile.isNotEmpty() && password.isNotEmpty()) {

                val loginRequest = LoginRequest(mobile, password)

                retrofitUnit.login(loginRequest) { response ->
                    if (response.isSuccessful && response.body() != null && response.body()?.data != null) {
                        val auth = response.body()?.data?.auth
                        val accessToken = auth?.accessToken

                        if (accessToken != null) {
                            // 登录成功
                            Toast.makeText(this, "$mobile 登录成功", Toast.LENGTH_SHORT).show()
                        } else {
                            // 登录失败
                            Toast.makeText(this, "$mobile 登录失败", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // 请求失败
                        Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show()
                        Log.e("MainActivity", "login() failed: ${response.errorBody()}")
                    }
                }
            } else {
                Toast.makeText(this, "手机号码或密码不能为空", Toast.LENGTH_SHORT).show()
            }
        }
    }


    }