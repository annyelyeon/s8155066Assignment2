package com.mypro.s8155066assignment2.uidashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mypro.s8155066assignment2.R
import com.mypro.s8155066assignment2.data.Entity

class MovieAdapter(
    private var movieList: List<Entity> = listOf(),
    private val onItemClick: (Entity) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position], onItemClick)
    }

    override fun getItemCount() = movieList.size

    fun updateData(newList: List<Entity>) {
        movieList = newList
        notifyDataSetChanged()
    }
}