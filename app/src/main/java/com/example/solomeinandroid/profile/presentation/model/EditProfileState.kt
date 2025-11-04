package com.example.solomeinandroid.profile.presentation.model

import android.net.Uri

interface EditProfileState {
    val photoUri: Uri
    val name: String
    val url: String
    val nick : String
    val isNeedToShowPermission: Boolean
    val isNeedToShowSelect: Boolean
}