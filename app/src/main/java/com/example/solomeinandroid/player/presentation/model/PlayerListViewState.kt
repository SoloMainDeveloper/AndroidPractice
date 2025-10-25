package com.example.solomeinandroid.player.presentation.model

data class PlayerListViewState (
    val state: State = State.Loading
) {
    sealed interface State {
        object Loading : State
        data class Error(val error : String) : State
        data class Success(val data : List<PlayerModel>) : State
    }
}