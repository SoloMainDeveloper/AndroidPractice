package com.example.solomeinandroid.player.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.solomeinandroid.Players
import com.example.solomeinandroid.navigation.Route
import com.example.solomeinandroid.navigation.TopLevelBackStack
import com.example.solomeinandroid.player.model.PlayerModel
import com.example.solomeinandroid.player.viewModel.PlayersListViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun PlayersListView(topLevelBackStack: TopLevelBackStack<Route>) {
    val viewModel = koinViewModel<PlayersListViewModel> {
        parametersOf(topLevelBackStack)
    }
    val players by viewModel.players.collectAsStateWithLifecycle()

    LazyColumn {
        items(
            items = players,
            key = { it.name }
        ) { player ->
            PlayersListItem(
                player = player,
                onPlayerClick = { viewModel.onPlayerClick(player) }
            )
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

@Preview(showBackground = true)
@Composable
fun PlayersListPreview() {
    PlayersListView(TopLevelBackStack(Players))
}