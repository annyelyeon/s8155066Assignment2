package com.mypro.s8155066assignment2.uilogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mypro.s8155066assignment2.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun login(
        username: String,
        password: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        if (username.isBlank() || password.isBlank()) {
            onError("Please enter username and password")
            return
        }

        viewModelScope.launch {
            try {
                val response = repository.login(username.trim(), password.trim())
                onSuccess(response.keypass)
            } catch (e: Exception) {
                onError("Login failed. Please check your details and connection.")
            }
        }
    }
}