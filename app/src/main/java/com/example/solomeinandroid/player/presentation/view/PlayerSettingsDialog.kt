package com.example.solomeinandroid.player.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.solomeinandroid.player.presentation.model.PlayerSettingsState
import com.example.solomeinandroid.player.presentation.viewModel.PlayerSettingsViewModel
import com.example.uikit.uikit.Spacing
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayerSettingsDialog() {
    val viewModel = koinViewModel<PlayerSettingsViewModel>()
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    PlayerSettingsDialog(
        state,
        viewModel::onPlayersRichFirstSortToggled,
        viewModel::onBack,
        viewModel::onSaveClicked,
    )
}

@Composable
fun PlayerSettingsDialog(
    state: PlayerSettingsState,
    onFilmsFirstCheckedChange: (Boolean) -> Unit = {},
    onBack: () -> Unit = {},
    onSaveClick: () -> Unit = {},
){
    Dialog(
        onDismissRequest = {onBack()}
    ) {
        Column(
            Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(Spacing.medium),
        ) {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.titleMedium,
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(
                    checked = state.richFirst,
                    onCheckedChange = { onFilmsFirstCheckedChange(it) }
                )

                Spacer(Modifier.width(Spacing.medium))
                Text("Rich first\n(Total winnings)")
            }

            TextButton(
                onClick = onSaveClick,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Save")
            }
        }
    }
}