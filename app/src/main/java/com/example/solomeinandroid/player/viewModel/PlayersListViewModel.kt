package com.example.solomeinandroid.player.viewModel

import androidx.lifecycle.ViewModel
import com.example.solomeinandroid.PlayerDetails
import com.example.solomeinandroid.navigation.Route
import com.example.solomeinandroid.navigation.TopLevelBackStack
import com.example.solomeinandroid.player.model.PlayerModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.solomeinandroid.player.getMockPlayers

class PlayersListViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>
) : ViewModel() {
    private val _players = MutableStateFlow<List<PlayerModel>>(emptyList())
    val players = _players.asStateFlow()

    init {
        loadPlayers()
    }

    private fun loadPlayers() {
        _players.value = getMockPlayers()
    }

    fun onPlayerClick(player: PlayerModel) {
        topLevelBackStack.add(PlayerDetails(player))
    }
}