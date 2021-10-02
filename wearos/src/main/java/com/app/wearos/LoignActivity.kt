package com.app.wearos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.app.wearos.databinding.ActivityLoignBinding

class LoignActivity : Activity() {

    private lateinit var binding: ActivityLoignBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = "dev"
        val password = "dev123"
        binding.btnLogin.setOnClickListener {
            login(username, password)
        }
    }

    private fun login(username: String, password: String) {
        if (username == binding.etUsername.text.toString() && password == binding.etPassword.text.toString()) {
            startActivity(Intent(this, DashbaordActivity::class.java))
        }
    }
}