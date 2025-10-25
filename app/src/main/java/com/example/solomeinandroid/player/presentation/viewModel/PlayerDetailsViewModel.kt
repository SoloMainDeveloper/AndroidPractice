package com.example.solomeinandroid.player.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.solomeinandroid.navigation.Route
import com.example.solomeinandroid.navigation.TopLevelBackStack
import com.example.solomeinandroid.player.presentation.model.PlayerDetailsModel
import com.example.solomeinandroid.player.presentation.model.PlayerModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlayerDetailsViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
    private val player: PlayerModel
): ViewModel() {
    private val mutableState = MutableStateFlow(PlayerDetailsModel(player))
    val state = mutableState.asStateFlow()

    fun onBack() {
        topLevelBackStack.removeLast()
    }
}