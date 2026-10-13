package com.mypro.s8155066assignment2.uilogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mypro.s8155066assignment2.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _keypass = MutableStateFlow<String?>(null)
    val keypass: StateFlow<String?> = _keypass

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun login(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            _errorState.value = "Please enter username and password"
            return
        }

        _errorState.value = null
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = repository.login(username.trim(), password.trim())
                _keypass.value = response.keypass
                _isLoading.value = false
            } catch (e: Exception) {
                _errorState.value = "Login failed. Please check your details and connection."
                _isLoading.value = false
            }
        }
    }
}