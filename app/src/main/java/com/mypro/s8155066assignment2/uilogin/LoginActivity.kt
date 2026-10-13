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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mypro.s8155066assignment2.R
import com.mypro.s8155066assignment2.uidashboard.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
            viewModel.login(etUsername.text.toString(), etPassword.text.toString())
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                // Loading progress bar
                launch {
                    viewModel.isLoading.collect { loading ->
                        progressBar.visibility = if (loading) View.VISIBLE else View.GONE
                        btnLogin.isEnabled = !loading
                    }
                }

                // error message
                launch {
                    viewModel.errorState.collect { message ->
                        if (message == null) {
                            tvError.visibility = View.GONE
                        } else {
                            tvError.text = message
                            tvError.visibility = View.VISIBLE
                        }
                    }
                }

                // get keypass move to dashboard
                launch {
                    viewModel.keypass.collect { keypass ->
                        if (keypass != null) {
                            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                            intent.putExtra("keypass", keypass)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
        }
    }
}