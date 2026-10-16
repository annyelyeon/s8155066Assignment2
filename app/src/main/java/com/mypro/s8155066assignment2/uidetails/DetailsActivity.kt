package com.mypro.s8155066assignment2.uidetails

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mypro.s8155066assignment2.R
import com.mypro.s8155066assignment2.data.Entity
import com.mypro.s8155066assignment2.data.summaryFields
import com.mypro.s8155066assignment2.data.toDisplayText

class DetailsActivity : AppCompatActivity() {

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val movie = intent.getSerializableExtra("movie") as? Entity
        if (movie == null) {
            finish()
            return
        }

        val fields = movie.summaryFields()
        findViewById<TextView>(R.id.tvTitle).text = fields.getOrNull(0)?.second ?: ""
        findViewById<TextView>(R.id.tvInfo).text =
            fields.drop(1).joinToString("\n") { "${it.first}: ${it.second}" }
        findViewById<TextView>(R.id.tvDescription).text = movie["description"].toDisplayText()
    }
}