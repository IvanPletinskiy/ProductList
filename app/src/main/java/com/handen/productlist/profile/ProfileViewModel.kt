package com.handen.productlist.profile

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    val avatarState = MutableStateFlow<AvatarState>(AvatarState.Empty)
    val name = MutableStateFlow("")
    val email = MutableStateFlow("")

    fun onNameChanged(name: String) {
        this.name.value = name
    }

    fun onEmailChanged(email: String) {
        this.email.value = email
    }

    fun onPictureTaken(bitmap: Bitmap) {
        avatarState.value = AvatarState.Taken(bitmap)
    }

    sealed class AvatarState {
        object Empty : AvatarState()
        data class Taken(val bitmap: Bitmap) : AvatarState()
    }
}