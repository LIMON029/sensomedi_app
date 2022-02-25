package com.limon.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Toast
import androidx.core.view.isGone
import com.limon.test.databinding.ActivityMainBinding
import com.limon.test.request.EmailRequestDto
import com.limon.test.request.JoinRequestDto
import com.limon.test.response.Response
import com.limon.test.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding ?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() = with(binding) {
        sendCheckEmailBtn.setOnClickListener { sendEmail() }
        joinButton.setOnClickListener { join() }
    }

    private fun join() = with(binding) {
        val address = emailEditText.text.toString()
        val code = codeEditText.text.toString().toInt()
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()
        val passwordCheck = passwordCheckEditText.text.toString()
        val param = JoinRequestDto(address, username, password, passwordCheck, code)
        RetrofitClient.instance.join(param)
            .enqueue(object: Callback<Response> {
                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>
                ) {
                    if(response.isSuccessful) {
                        val result = response.body()
                            ?: throw Exception("There is no body")
                        binding.doneMsg.isGone = false
                        if (result.getCode() != 200) {
                            binding.doneMsg.text = result.getMessage()
                        }
                    }
                }

                override fun onFailure(call: Call<Response>, t: Throwable) {
                    Log.e("retrofit", "Join Failed")
                }

            })
    }

    private fun sendEmail() = with(binding){
        val param = EmailRequestDto(emailEditText.text.toString())
        RetrofitClient.instance.sendCheckMail(param)
            .enqueue(object: Callback<Response> {
                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>
                ) {
                    if(response.isSuccessful) {
                        val result = response.body()
                            ?:throw Exception("There is no body")
                        if(result.getCode()==200){
                            codeEditText.inputType = InputType.TYPE_CLASS_NUMBER
                            emailEditText.inputType = InputType.TYPE_NULL
                            sendCheckEmailBtn.isClickable = false
                            Toast.makeText(this@MainActivity, "인증코드가 발송되었습니다.", Toast.LENGTH_SHORT).show()
                        } else {
                            doneMsg.isGone = false
                            doneMsg.text = result.getMessage()
                        }
                    }
                }

                override fun onFailure(call: Call<Response>, t: Throwable) {
                    Log.e("retrofit", "sendMail failed")
                    Log.e("retrofit", t.message!!)
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}