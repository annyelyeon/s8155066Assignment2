package com.mypro.s8155066assignment2.uidetails

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mypro.s8155066assignment2.R
import com.mypro.s8155066assignment2.data.Entity

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val movie = intent.getSerializableExtra("movie") as? Entity
        if (movie == null) {
            finish()
            return
        }

        findViewById<TextView>(R.id.tvTitle).text = movie.title
        findViewById<TextView>(R.id.tvDirector).text = "Director: ${movie.director}"
        findViewById<TextView>(R.id.tvGenre).text = "Genre: ${movie.genre}"
        findViewById<TextView>(R.id.tvYear).text = "Year: ${movie.releaseYear}"
        findViewById<TextView>(R.id.tvDescription).text = movie.description
    }
}