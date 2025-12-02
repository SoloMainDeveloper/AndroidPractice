package com.example.solomeinandroid.player.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.solomeinandroid.player.presentation.model.PlayerListFilter
import com.example.solomeinandroid.player.presentation.model.PlayerListViewState
import com.example.solomeinandroid.player.presentation.model.PlayerModel
import com.example.solomeinandroid.player.presentation.viewModel.PlayersListViewModel
import com.example.uikit.uikit.FullScreenError
import com.example.uikit.uikit.FullScreenLoading
import com.example.uikit.uikit.Spacing
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayersListView() {
    val viewModel = koinViewModel<PlayersListViewModel>()
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val showBadge by viewModel.showBadge.collectAsStateWithLifecycle()

    PlayerListScreenContent(
        state,
        viewModel::onPlayerClick,
        viewModel::onRetryClick,
        viewModel::onSettingsClick,
        viewModel::onFilterChange,
        showBadge = showBadge
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlayerListScreenContent(
    state: PlayerListViewState,
    onPlayerClick: (PlayerModel) -> Unit = {},
    onRetryClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onFilterChange: (PlayerListFilter) -> Unit = {},
    showBadge: Boolean = false
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        floatingActionButton = {
            BadgedBox(
                badge = {
                    if (showBadge) {
                        Badge(
                            containerColor = Color.Red,
                            modifier = Modifier.size(8.dp)
                        )
                    }
                }
            ) {
                FloatingActionButton(onClick = { onSettingsClick() }) {
                    Icon(Icons.Default.Settings, "SettingsBtn")
                }
            }
        },
            topBar = {
            TopAppBar(
                { PlayerListFilters(state, onFilterChange)},
                scrollBehavior = scrollBehavior
            )
        },
        contentWindowInsets = WindowInsets(0.dp),
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { it ->
        Box(Modifier.padding(it)) {
            when (state.listState) {
                PlayerListViewState.State.Loading -> {
                    FullScreenLoading()
                }

                is PlayerListViewState.State.Success -> {
                    LazyColumn {
                        state.listState.data.forEach { player ->
                            item {
                                PlayersListItem(player) {
                                    onPlayerClick(it)
                                }
                            }
                        }
                    }
                }

                is PlayerListViewState.State.Error -> {
                    FullScreenError(
                        retry = { onRetryClick() },
                        text = state.listState.error
                    )
                }
            }
        }
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlayersListItem(player: PlayerModel, onPlayerClick: (PlayerModel) -> Unit) {
    Column(
        modifier = Modifier.padding(bottom = 0.dp).clickable { onPlayerClick(player) }
    ) {
        Row (modifier = Modifier.padding(12.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically)
        {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = player.nick,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = String.format("Nationality: %s", player.nationality),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = String.format("Age: %s", player.age),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            GlideImage(
                model = player.team.imageUrl,
                contentDescription = String.format("%s logo", player.team.name),
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit,
            )
        }
        HorizontalDivider()
    }
}

@Composable
private fun PlayerListFilters(
    state: PlayerListViewState,
    onFilterChange: (PlayerListFilter) -> Unit,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(Spacing.small)
    ) {
        state.filters.forEach { filter ->
            FilterChip(
                selected = filter == state.currentFilter,
                label = {Text(filter.text)},
                onClick = {onFilterChange(filter)},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayersListPreview() {
    PlayersListView()
}