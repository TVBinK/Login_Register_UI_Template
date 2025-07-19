package com.example.learnjetpack.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoginSuccessful: Boolean = false
)

class LoginViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()
    
    fun updateEmail(email: String) {
        _uiState.update { it.copy(email = email, errorMessage = null) }
    }
    
    fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password, errorMessage = null) }
    }
    
    fun togglePasswordVisibility() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }
    
    fun login() {
        val currentState = _uiState.value
        
        // Validate inputs
        if (currentState.email.isEmpty()) {
            _uiState.update { it.copy(errorMessage = "Email is required") }
            return
        }
        
        if (!isValidEmail(currentState.email)) {
            _uiState.update { it.copy(errorMessage = "Please enter a valid email") }
            return
        }
        
        if (currentState.password.isEmpty()) {
            _uiState.update { it.copy(errorMessage = "Password is required") }
            return
        }
        
        if (currentState.password.length < 6) {
            _uiState.update { it.copy(errorMessage = "Password must be at least 6 characters") }
            return
        }
        
        // Start loading
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        
        // Simulate login process
        // In a real app, you would make an API call here
        // For now, we'll simulate a successful login after a delay
        // You can replace this with actual authentication logic
        
        // Example of how you might handle this with coroutines:
        /*
        viewModelScope.launch {
            try {
                val result = authRepository.login(currentState.email, currentState.password)
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        isLoginSuccessful = true,
                        errorMessage = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Login failed"
                    )
                }
            }
        }
        */
        
        // For demo purposes, simulate success
        _uiState.update { 
            it.copy(
                isLoading = false,
                isLoginSuccessful = true,
                errorMessage = null
            )
        }
    }
    
    fun facebookLogin() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        
        // Simulate Facebook login
        // In a real app, you would integrate with Facebook SDK
        _uiState.update { 
            it.copy(
                isLoading = false,
                isLoginSuccessful = true,
                errorMessage = null
            )
        }
    }
    
    fun googleLogin() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        
        // Simulate Google login
        // In a real app, you would integrate with Google Sign-In
        _uiState.update { 
            it.copy(
                isLoading = false,
                isLoginSuccessful = true,
                errorMessage = null
            )
        }
    }
    
    fun forgotPassword() {
        val currentState = _uiState.value
        
        if (currentState.email.isEmpty()) {
            _uiState.update { it.copy(errorMessage = "Please enter your email first") }
            return
        }
        
        if (!isValidEmail(currentState.email)) {
            _uiState.update { it.copy(errorMessage = "Please enter a valid email") }
            return
        }
        
        // Handle forgot password logic
        // In a real app, you would make an API call to send reset email
        _uiState.update { it.copy(errorMessage = "Password reset email sent!") }
    }
    
    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }
    
    fun resetLoginState() {
        _uiState.update { it.copy(isLoginSuccessful = false) }
    }
    
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
} 