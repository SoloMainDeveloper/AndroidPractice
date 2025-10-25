package com.example.solomeinandroid.player.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solomeinandroid.navigation.Route
import com.example.solomeinandroid.navigation.TopLevelBackStack
import com.example.solomeinandroid.player.domain.interactor.PlayerInteractor
import com.example.solomeinandroid.player.domain.model.PlayerEntity
import com.example.solomeinandroid.player.presentation.model.PlayerDetailsViewState
import com.example.solomeinandroid.player.presentation.model.PlayerModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayerDetailsViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
    private val player: PlayerModel,
    private val interactor: PlayerInteractor
): ViewModel() {
    private val mutableState = MutableStateFlow(PlayerDetailsViewState(player))
    val state = mutableState.asStateFlow()

    init {
        viewModelScope.launch {
            val isFavorite = interactor.isFavorite(player.nick)
            mutableState.update { it.copy(isFavorite = isFavorite) }
        }
    }

    fun onBack() {
        topLevelBackStack.removeLast()
    }

    fun onToggleFavorite(){
        viewModelScope.launch {
            val currentlyIsFavorite = !state.value.isFavorite
            mutableState.update { it.copy(isFavorite = currentlyIsFavorite) }

            if(currentlyIsFavorite){
                interactor.addFavorite(
                    PlayerEntity(
                        name = player.name,
                        nick = player.nick,
                        nationality = player.nationality,
                        imageUrl = player.imageUrl,
                        age = player.age,
                        team = player.team,
                        totalWinningsDollars = player.totalWinningsDollars,
                        info = player.info
                    )
                )
            } else {
                interactor.removeFromFavorites(player.nick)
            }
        }
    }
}