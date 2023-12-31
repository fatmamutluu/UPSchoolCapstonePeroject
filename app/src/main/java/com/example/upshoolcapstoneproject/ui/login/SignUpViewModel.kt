
package com.example.upshoolcapstoneproject.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.upshoolcapstoneproject.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    private val _signUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> get() = _signUpState

    fun signUp(email: String, password: String) = viewModelScope.launch {
        _signUpState.value = SignUpState.Loading

        _signUpState.value = when (val result = authRepository.signUp(email, password)) {
            is Resource.Success<*> -> SignUpState.SuccessState
            is Resource.Error -> SignUpState.ShowSnackbar(result.errorMessage)
            is Resource.Fail -> SignUpState.ShowSnackbar(result.failMessage)

        }
    }

    fun checkInfo(email: String, password: String) {
        when {
            email.isEmpty() ->  {
                _signUpState.value = SignUpState.ShowSnackbar("Email can not be empty")
            }

            password.isEmpty() -> {
                _signUpState.value = SignUpState.ShowSnackbar("Password can not be empty")
                false
            }

            password.length < 6 -> {
                _signUpState.value = SignUpState.ShowSnackbar("Password can not be less than 6 characters")
                false
            }

            else -> {
                signUp(email, password)
            }
        }
    }
}

sealed interface SignUpState {
    object Loading : SignUpState
    object SuccessState : SignUpState
    data class ShowSnackbar(val errorMessage: String) : SignUpState
}
