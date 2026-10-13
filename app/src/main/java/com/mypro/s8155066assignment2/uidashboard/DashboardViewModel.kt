package com.mypro.s8155066assignment2.uidashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mypro.s8155066assignment2.data.Entity
import com.mypro.s8155066assignment2.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Entity>>(emptyList())
    val movies: StateFlow<List<Entity>> = _movies

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadMovies(keypass: String) {
        _errorState.value = null
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = repository.getDashboard(keypass)
                _movies.value = response.entities
                _isLoading.value = false
            } catch (e: Exception) {
                _errorState.value = "Failed to load movies. Please check your connection."
                _isLoading.value = false
            }
        }
    }
}