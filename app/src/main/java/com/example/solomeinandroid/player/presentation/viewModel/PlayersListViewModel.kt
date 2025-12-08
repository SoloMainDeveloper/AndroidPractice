package com.example.solomeinandroid.player.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solomeinandroid.PlayerDetails
import com.example.solomeinandroid.PlayerSettings
import com.example.core.navigation.Route
import com.example.core.navigation.TopLevelBackStack
import com.example.solomeinandroid.player.data.cache.BadgeCache
import com.example.solomeinandroid.player.domain.interactor.PlayerInteractor
import com.example.solomeinandroid.player.domain.model.PlayerEntity
import com.example.solomeinandroid.player.presentation.model.PlayerListFilter
import com.example.solomeinandroid.player.presentation.model.PlayerModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.solomeinandroid.player.presentation.model.PlayerListViewState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class PlayersListViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
    private val interactor: PlayerInteractor,
    private val badgeCache: BadgeCache
) : ViewModel() {
    private val mutableState = MutableStateFlow(PlayerListViewState())
    val viewState = mutableState.asStateFlow()
    val showBadge: StateFlow<Boolean> = badgeCache.hasActiveFilters

    init {
        loadPlayers()
        syncBadgeWithSettings()
    }

    private fun syncBadgeWithSettings() {
        viewModelScope.launch {
            interactor.observePlayerFirstSettings().collect { richFirst ->
                badgeCache.setFiltersActive(richFirst)
            }
        }
    }

    fun onPlayerClick(player: PlayerModel) {
        topLevelBackStack.add(PlayerDetails(player))
    }

    fun onRetryClick() = loadPlayers()

    fun onSettingsClick(){
        topLevelBackStack.add(PlayerSettings)
    }

    fun onFilterChange(filter: PlayerListFilter) {
        mutableState.update { it.copy(currentFilter = filter) }
        loadPlayers()
    }

    private fun loadPlayers() {
        viewModelScope.launch {
            try {
                updateState(PlayerListViewState.State.Loading)
                interactor.observePlayerFirstSettings()
                    .onEach { updateState(PlayerListViewState.State.Loading) }
                    .map {
                        if (viewState.value.currentFilter == PlayerListFilter.ALL){
                            interactor.getPlayerList(it)
                        } else {
                            interactor.getFavorites()
                        }
                    }
                    .collect{ playerList ->
                        updateState(PlayerListViewState.State.Success(mapToUi(playerList)))
                    }
            } catch (e: Exception) {
                updateState(PlayerListViewState.State.Error("An error has occurred"))
            }
        }
    }

    private fun updateState(state: PlayerListViewState.State) =
        mutableState.update { it.copy(listState = state) }

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