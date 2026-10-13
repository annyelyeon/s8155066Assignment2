package com.mypro.s8155066assignment2.uidashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mypro.s8155066assignment2.data.Entity
import com.mypro.s8155066assignment2.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun loadMovies(
        keypass: String,
        onSuccess: (List<Entity>) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = repository.getDashboard(keypass)
                onSuccess(response.entities)
            } catch (e: Exception) {
                onError("Failed to load movies. Please check your connection.")
            }
        }
    }
}