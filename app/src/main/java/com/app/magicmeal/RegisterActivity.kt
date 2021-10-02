package com.app.magicmeal

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.app.magicmeal.model.User
import com.app.magicmeal.repository.UserRepo
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.etEmail
import kotlinx.android.synthetic.main.activity_login.etPassword
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.etFullName
import kotlinx.android.synthetic.main.activity_profile.etLocation
import kotlinx.android.synthetic.main.activity_profile.etPhone
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class RegisterActivity : AppCompatActivity() {

    private val permissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.INTERNET
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btnRegister.setOnClickListener {
            register()
        }
        if (!hasPermission()) {
            requestPermission()
        }
    }

    private fun register() {

        val fullName = etFullName.text.toString()
        val email = etEmail.text.toString()
        val phone = etPhone.text.toString()
        val place = etLocation.text.toString()
        val password = etPassword.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepo = UserRepo()
                val user = User(
                    username = fullName,
                    email = email,
                    phone_no = phone,
                    location = place,
                    password = password
                )
                val response = userRepo.register(user)
                if (response.success == true) {
                    withContext(Main) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Account Created! Keep Eating !!!",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    }
                }
            } catch (ex: IOException) {
                withContext(Main) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Registration Failed ! ${ex.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    //Request Permission
    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this@RegisterActivity,
            permissions, 1434
        )
    }


    //Check If Permission is given
    private fun hasPermission(): Boolean {
        var hasPermission = true
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                hasPermission = false
            }
        }
        return hasPermission
    }
}