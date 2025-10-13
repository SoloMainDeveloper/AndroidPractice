package com.example.solomeinandroid.player.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.solomeinandroid.player.model.PlayerDetailsModel
import com.example.solomeinandroid.player.model.PlayerModel
import com.example.solomeinandroid.player.viewModel.PlayerDetailsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerDetailsView(player: PlayerModel) {
    val viewModel = koinViewModel<PlayerDetailsViewModel> {
        parametersOf(player)
    }
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Players") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            viewModel.onBack()
                        }
                    )
                }
            )
        }
    ) { padding ->
        CharacterDetailsContent(
            state = state,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterDetailsContent(state: PlayerDetailsModel, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Column (modifier = modifier
        .verticalScroll(scrollState)){
        Row{
            GlideImage(
                model = state.player.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = state.player.name,
                    style = MaterialTheme.typography.titleLarge
                )
                listOf(
                    "Nationality: ${state.player.nationality}",
                    "Team: ${state.player.team.name}",
                    "Age: ${state.player.age}",
                    "Winnings: ${state.player.totalWinningsDollars}"
                ).forEach { text ->
                    Text(
                        text = text,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
        Text(
            modifier = Modifier.padding(10.dp),
            text = state.player.info,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}