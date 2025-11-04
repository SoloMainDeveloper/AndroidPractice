package com.example.solomeinandroid.player.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solomeinandroid.navigation.Route
import com.example.solomeinandroid.navigation.TopLevelBackStack
import com.example.solomeinandroid.player.data.cache.BadgeCache
import com.example.solomeinandroid.player.domain.interactor.PlayerInteractor
import com.example.solomeinandroid.player.presentation.model.PlayerSettingsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayerSettingsViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
    private val interactor: PlayerInteractor,
    private val badgeCache: BadgeCache
): ViewModel() {
    private val mutableState = MutableStateFlow(PlayerSettingsState())
    val viewState = mutableState.asStateFlow()

    init {
        viewModelScope.launch {
            interactor.observePlayerFirstSettings().collect { richPlayersFirst ->
                mutableState.update { it.copy(richFirst = richPlayersFirst) }
            }
        }
    }

    fun onPlayersRichFirstSortToggled(isChecked: Boolean) {
        mutableState.update {
            it.copy(richFirst = isChecked)
        }
    }

    fun onBack() {
        topLevelBackStack.removeLast()
    }

    fun onSaveClicked() {
        viewModelScope.launch {
            interactor.setRichPlayersFirstSetting(viewState.value.richFirst)
            onBack()
        }
    }

    fun onResetClicked() {
        viewModelScope.launch {
            interactor.setRichPlayersFirstSetting(false)
            badgeCache.setFiltersActive(false)
            mutableState.update { it.copy(richFirst = false) }
        }
    }
}