package com.example.solomeinandroid.player.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solomeinandroid.PlayerDetails
import com.example.solomeinandroid.navigation.Route
import com.example.solomeinandroid.navigation.TopLevelBackStack
import com.example.solomeinandroid.player.domain.interactor.PlayerInteractor
import com.example.solomeinandroid.player.domain.model.PlayerEntity
import com.example.solomeinandroid.player.presentation.model.PlayerModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.solomeinandroid.player.presentation.model.PlayerListViewState
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayersListViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
    private val interactor: PlayerInteractor
) : ViewModel() {
    private val mutableState = MutableStateFlow(PlayerListViewState())
    val viewState = mutableState.asStateFlow()

    init {
        loadPlayers()
    }

    fun onPlayerClick(player: PlayerModel) {
        topLevelBackStack.add(PlayerDetails(player))
    }

    fun onRetryClick() = loadPlayers()

    private fun loadPlayers() {
        viewModelScope.launch {
            try {
                updateState(PlayerListViewState.State.Loading)
                val playerList = interactor.getPlayerList()
                updateState(PlayerListViewState.State.Success(mapToUi(playerList)))
            } catch (e: Exception) {
                updateState(PlayerListViewState.State.Error("An error has occurred"))
            }
        }
    }

    private fun updateState(state: PlayerListViewState.State) =
        mutableState.update { it.copy(state = state) }

    private fun mapToUi(players: List<PlayerEntity>): List<PlayerModel> = players.map { player ->
        PlayerModel(
            name = player.name,
            nick = player.nick,
            nationality = player.nationality,
            imageUrl  = player.imageUrl,
            age = player.age,
            team = player.team,
            totalWinningsDollars = player.totalWinningsDollars,
            info = player.info
        )
    }
}