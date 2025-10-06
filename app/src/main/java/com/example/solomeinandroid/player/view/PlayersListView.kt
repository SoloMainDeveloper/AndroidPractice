package com.example.solomeinandroid.player.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.solomeinandroid.PlayerDetails
import com.example.solomeinandroid.Players
import com.example.solomeinandroid.player.getMockPlayer
import com.example.solomeinandroid.navigation.Route
import com.example.solomeinandroid.navigation.TopLevelBackStack
import com.example.solomeinandroid.player.model.PlayerModel

@Composable
fun PlayersListView(topLevelBackStack: TopLevelBackStack<Route>) {
    val players = remember { getMockPlayer() }

    LazyColumn {
        players.forEach { player ->
            item(key = player.name) {
                PlayersListItem(
                    player,
                    onPlayerClick = {
                        topLevelBackStack.add(PlayerDetails(player))
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlayersListItem(player: PlayerModel, onPlayerClick: (PlayerModel) -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onPlayerClick(player) }
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically)
        {
            Column(
                modifier = Modifier.weight(1f).padding(horizontal = 8.dp).padding(end = 16.dp)
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