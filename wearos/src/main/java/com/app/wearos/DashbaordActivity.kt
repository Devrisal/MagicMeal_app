package com.app.wearos

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.wearos.databinding.ActivityLoignBinding

class DashbaordActivity : Activity() {
    private lateinit var binding: ActivityLoignBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoignBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}