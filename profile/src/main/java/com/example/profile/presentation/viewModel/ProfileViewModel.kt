package com.example.profile.presentation.viewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profile.data.repository.ProfileRepository
import com.example.profile.presentation.model.ProfileState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository
): ViewModel() {
    private val _navigationEvent = MutableSharedFlow<String>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun onEditProfileClicked() {
        viewModelScope.launch {
            _navigationEvent.emit("edit_profile")
        }
    }
    private val mutableState = MutableProfileState()
    val viewState = mutableState as ProfileState

    init {
        viewModelScope.launch {
            repository.observeProfile().collect {
                mutableState.name = it.name
                mutableState.photoUri = Uri.parse(it.photoUri)
                mutableState.nick = it.nick
                mutableState.url = it.url
                mutableState.lessonTime = it.lessonTime
            }
        }
    }

    private class MutableProfileState: ProfileState {
        override var name by mutableStateOf("")
        override var photoUri by mutableStateOf(Uri.EMPTY)
        override var url by mutableStateOf("")
        override var nick by mutableStateOf("")
        override var lessonTime by mutableStateOf("")
    }
}