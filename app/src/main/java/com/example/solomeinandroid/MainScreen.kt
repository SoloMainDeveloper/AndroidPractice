package com.example.solomeinandroid

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.solomeinandroid.navigation.Route
import com.example.solomeinandroid.navigation.TopLevelBackStack
import com.example.solomeinandroid.player.presentation.model.PlayerModel
import com.example.solomeinandroid.player.presentation.view.PlayerDetailsView
import com.example.solomeinandroid.player.presentation.view.PlayersListView
import com.example.solomeinandroid.tournament.view.TournamentListView
import org.koin.java.KoinJavaComponent.inject

data class PlayerDetails(val player : PlayerModel) : Route

interface TopLevelRoute : Route {
    val icon: ImageVector
}

data object Tournaments : TopLevelRoute {
    override val icon = Icons.Default.DateRange
}

data object Players : TopLevelRoute {
    override val icon = Icons.Default.Person
}

@Composable
fun MainScreen() {
    val topLevelBackStack by inject<TopLevelBackStack<Route>>(TopLevelBackStack::class.java)

    Scaffold(bottomBar = {
        NavigationBar {
            listOf(Players, Tournaments).forEach { route ->
                NavigationBarItem(
                    icon = { Icon(route.icon, null) },
                    selected = topLevelBackStack.topLevelKey == route,
                    onClick = {
                        topLevelBackStack.addTopLevel(route)
                    }
                )
            }
        }
    }) { padding ->
        NavDisplay(
            backStack = topLevelBackStack.backStack,
            onBack = { topLevelBackStack.removeLast() },
            modifier = Modifier.padding(padding),
            entryDecorators = listOf(
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<Players> {
                    PlayersListView()
                }
                entry<PlayerDetails> {
                    PlayerDetailsView(it.player)
                }
                entry<Tournaments> {
                    TournamentListView()
                }
            }
        )
    }
}
