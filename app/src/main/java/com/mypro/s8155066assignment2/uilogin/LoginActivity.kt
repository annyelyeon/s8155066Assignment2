package com.mypro.s8155066assignment2.uilogin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mypro.s8155066assignment2.R
import com.mypro.s8155066assignment2.uidashboard.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val tvError = findViewById<TextView>(R.id.tvError)

        btnLogin.setOnClickListener {
            tvError.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            btnLogin.isEnabled = false

            viewModel.login(
                etUsername.text.toString(),
                etPassword.text.toString(),
                onSuccess = { keypass ->
                    val intent = Intent(this, DashboardActivity::class.java)
                    intent.putExtra("keypass", keypass)
                    startActivity(intent)
                    finish()
                },
                onError = { message ->
                    progressBar.visibility = View.GONE
                    btnLogin.isEnabled = true
                    tvError.text = message
                    tvError.visibility = View.VISIBLE
                }
            )
        }
    }
}