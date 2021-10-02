package com.app.magicmeal

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.app.magicmeal.api.ServiceBuilder
import com.app.magicmeal.model.User
import com.app.magicmeal.notification.NotificationChannel
import com.app.magicmeal.repository.UserRepo
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class LoginActivity : AppCompatActivity(), SensorEventListener {
    companion object {
        var _id: String? = null
    }

    private lateinit var sensorManager: SensorManager;
    private var sensor: Sensor? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        linkRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener {
            login()
        }

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager;
        if (!checkSensor()) {
            return
        } else {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            sensorManager.registerListener(
                this@LoginActivity,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
            );

        }


    }

    override fun onSensorChanged(event: SensorEvent?) {
        val values = event!!.values[0];
        if (values <= 1) {
            val builder = AlertDialog.Builder(this);
            builder.setTitle("MisOperation Mode Triggered")
            builder.setMessage("Do not block the top of the screen");
            builder.setIcon(android.R.drawable.ic_dialog_alert);

            var alert = builder.create();
            alert.setCancelable(true);
            alert.show();
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    private fun login() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepo = UserRepo()
                val response = userRepo.login(email, password)
                if (response.success == true) {
                    withContext(Main) {

                        ServiceBuilder.userData = response.Data
                        ServiceBuilder.token = "Bearer " + response.token
                        _id = response.Data!!._id.toString()

                        startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                        Notification(response.Data.username!!)
                        Toast.makeText(
                            this@LoginActivity,
                            "Welcome ${response.Data!!.username}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

            } catch (ex: IOException) {
                withContext(Main) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Login Failed ! ${ex.localizedMessage}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    vibrateOnError()
                }
            }
        }
    }

    private fun Notification(user: String) {

        val manager = NotificationManagerCompat.from(this);
        val channels = NotificationChannel(this);
        channels.createNotificationChannels();
        val notification = NotificationCompat.Builder(this, channels.CHANNEL_1)
            .setSmallIcon(R.drawable.ic_app_notification)
            .setContentTitle("Hi There !")
            .setContentText("Hey ${user},  Welcome to Cooking Corner !")
            .setColor(Color.GREEN)
            .build();
        manager.notify(1, notification);

    }

    private fun vibrateOnError() {
        val vibrator = this?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(2000, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(2000)
        }
    }

    private fun checkSensor(): Boolean {
        var flag = true;
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) == null) {
            flag = false
        }
        return flag;
    }


}