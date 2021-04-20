package com.internshala.activitylifecycle

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    lateinit var etmobile: EditText
    lateinit var etpassword: EditText
    lateinit var btnlogin: Button
    lateinit var txtforget: TextView
    lateinit var txtNewUser: TextView

    val validmobile = "0123456789"
    val validpassword = arrayOf<String>("tony","steve","thor","thanos")

   lateinit var sharedpreference : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedpreference = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)

        val isLoggedIn=sharedpreference.getBoolean("isLoggedIn",false)
        setContentView(R.layout.activity_login)

        if (isLoggedIn) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        title = "Login In"

        etmobile = findViewById(R.id.etmobile)
        etpassword = findViewById(R.id.etpassword)
        btnlogin = findViewById(R.id.btnlogin)
        txtforget = findViewById(R.id.txtforget)
        txtNewUser = findViewById(R.id.txtNewUser)

        btnlogin.setOnClickListener {

            val mobile = etmobile.text.toString()
            val password = etpassword.text.toString()
            var nameofavenger = "Avenger"
            val intent = Intent(this@LoginActivity, MainActivity::class.java)

            if ((mobile == validmobile)) {
                if (password == validpassword[0]) {
                    nameofavenger = "Iron man"
                    savePreferences(nameofavenger)
                    startActivity(intent)
                } else if (password == validpassword[1]) {
                    nameofavenger = "Captain America"
                    savePreferences(nameofavenger)
                    startActivity(intent)
                } else if (password == validpassword[2]) {

                    nameofavenger = "Thor"
                    savePreferences(nameofavenger)
                    startActivity(intent)
                } else if (password == validpassword[3]) {
                    nameofavenger = "The Avengers"
                    savePreferences(nameofavenger)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "Wrong Password", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    fun savePreferences(title:String) {
        sharedpreference.edit().putBoolean("isLoggedIn", true).apply()
        sharedpreference.edit().putString("Title", title).apply()
    }
}