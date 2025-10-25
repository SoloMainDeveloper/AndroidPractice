package com.example.solomeinandroid.player.presentation.model

data class PlayerListViewState (
    val listState: State = State.Loading,
    val filters: List<PlayerListFilter> = PlayerListFilter.entries,
    val currentFilter: PlayerListFilter = PlayerListFilter.ALL
) {
    sealed interface State {
        object Loading : State
        data class Error(val error : String) : State
        data class Success(val data : List<PlayerModel>) : State
    }
}

enum class PlayerListFilter(val text: String) {
    ALL("All"),
    FAVORITE("Favorite")
}