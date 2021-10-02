package com.app.magicmeal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.app.magicmeal.api.ServiceBuilder
import com.app.magicmeal.model.User
import com.app.magicmeal.repository.UserRepo
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        tvEdit.setOnClickListener {
            enableField()
        }
        btnUpdate.setOnClickListener {
            updateProfile()
        }
        disableFields()
        loadProfile()
    }

    private fun updateProfile() {

        val fullName = etFullName.text.toString()
        val phone = etPhone.text.toString()
        val address = etLocation.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepo = UserRepo()
                val user =
                    User(
                        username = fullName,
                        phone_no = phone,
                        location = address
                    )
                val response = userRepo.update(user)
                if (response.success == true) {
                    withContext(Main) {
                        Toast.makeText(this@ProfileActivity, "Profile Updated", Toast.LENGTH_SHORT)
                            .show()
                        loadProfile()
                    }
                }

            } catch (ex: IOException) {
                withContext(Main) {
                    Toast.makeText(
                        this@ProfileActivity,
                        "Error Updating Profile",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    private fun disableFields() {
        etFullName.isEnabled = false
        etPhone.isEnabled = false
        etLocation.isEnabled = false
        btnUpdate.visibility = View.GONE
    }

    private fun enableField() {
        etFullName.isEnabled = true
        etPhone.isEnabled = true
        etLocation.isEnabled = true
        btnUpdate.visibility = View.VISIBLE
    }

    private fun loadProfile() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepo = UserRepo()
                val response = userRepo.profile(LoginActivity._id.toString())
                if (response.success == true) {
                    withContext(Main) {
                        if (response.data != null) {
                            etFullName.setText(response.data.username.toString())
                            etLocation.setText(response.data.location.toString())
                            etPhone.setText(response.data.phone_no.toString())
                        }
                    }
                }

            } catch (ex: IOException) {
                withContext(Main) {
                    Toast.makeText(
                        this@ProfileActivity,
                        "Error Fetching Profile",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.miLogout -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        return true
    }


}