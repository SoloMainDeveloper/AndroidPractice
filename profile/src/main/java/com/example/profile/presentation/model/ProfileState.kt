package com.example.profile.presentation.model

import android.net.Uri

interface ProfileState {
    val name: String
    val photoUri: Uri
    val url: String
    val nick : String
    val lessonTime : String
}