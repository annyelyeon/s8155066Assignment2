package com.mypro.s8155066assignment2.uidashboard

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mypro.s8155066assignment2.R
import com.mypro.s8155066assignment2.data.Entity
import com.mypro.s8155066assignment2.data.summaryFields

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    private val tvDirector: TextView = view.findViewById(R.id.tvDirector)
    private val tvGenre: TextView = view.findViewById(R.id.tvGenre)
    private val tvYear: TextView = view.findViewById(R.id.tvYear)

    fun bind(movie: Entity, onItemClick: (Entity) -> Unit) {
        val fields = movie.summaryFields()

        tvTitle.text = fields.getOrNull(0)?.second ?: ""
        showLine(tvDirector, fields.getOrNull(1))
        showLine(tvGenre, fields.getOrNull(2))
        showLine(tvYear, fields.getOrNull(3))

        itemView.setOnClickListener { onItemClick(movie) }
    }

    private fun showLine(textView: TextView, field: Pair<String, String>?) {
        if (field == null) {
            textView.visibility = View.GONE
        } else {
            textView.visibility = View.VISIBLE
            textView.text = "${field.first}: ${field.second}"
        }
    }
}