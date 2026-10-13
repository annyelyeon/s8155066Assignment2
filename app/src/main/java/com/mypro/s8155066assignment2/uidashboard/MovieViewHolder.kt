package com.mypro.s8155066assignment2.uidashboard

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mypro.s8155066assignment2.R
import com.mypro.s8155066assignment2.data.Entity

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    private val tvDirector: TextView = view.findViewById(R.id.tvDirector)
    private val tvGenre: TextView = view.findViewById(R.id.tvGenre)
    private val tvYear: TextView = view.findViewById(R.id.tvYear)

    fun bind(movie: Entity, onItemClick: (Entity) -> Unit) {
        tvTitle.text = movie.title
        tvDirector.text = "Director: ${movie.director}"
        tvGenre.text = "Genre: ${movie.genre}"
        tvYear.text = "Year: ${movie.releaseYear}"

        itemView.setOnClickListener { onItemClick(movie) }
    }
}