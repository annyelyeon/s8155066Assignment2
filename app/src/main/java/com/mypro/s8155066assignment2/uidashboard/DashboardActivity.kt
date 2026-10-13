package com.mypro.s8155066assignment2.uidashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.mypro.s8155066assignment2.R
import com.mypro.s8155066assignment2.uidetails.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val tvError = findViewById<TextView>(R.id.tvError)

        adapter = MovieAdapter(onItemClick = { movie ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("movie", movie)
            startActivity(intent)
        })
        recyclerView.adapter = adapter

        val keypass = intent.getStringExtra("keypass") ?: ""
        viewModel.loadMovies(keypass)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.isLoading.collect { loading ->
                        progressBar.visibility = if (loading) View.VISIBLE else View.GONE
                    }
                }

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

                launch {
                    viewModel.movies.collect { movies ->
                        adapter.updateData(movies)
                    }
                }
            }
        }
    }
}