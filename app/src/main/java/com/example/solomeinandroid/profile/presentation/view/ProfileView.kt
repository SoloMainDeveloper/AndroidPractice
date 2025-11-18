package com.example.solomeinandroid.profile.presentation.view

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.os.Environment
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import org.koin.compose.viewmodel.koinViewModel
import kotlinx.parcelize.Parcelize
import java.io.File
import androidx.core.content.FileProvider
import com.example.solomeinandroid.EditProfile
import com.example.solomeinandroid.R
import com.example.solomeinandroid.navigation.Route
import com.example.solomeinandroid.navigation.TopLevelBackStack
import com.example.solomeinandroid.profile.presentation.BroadcastReceiver
import com.example.solomeinandroid.profile.presentation.viewModel.ProfileViewModel
import org.koin.java.KoinJavaComponent.inject
import androidx.core.net.toUri
import com.example.solomeinandroid.profile.presentation.model.ProfileState

@Parcelize
class ProfileScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
    @Composable
    override fun Content(modifier: Modifier) {
        val topLevelBackStack by inject<TopLevelBackStack<Route>>(clazz = TopLevelBackStack::class.java)
        val viewModel = koinViewModel<ProfileViewModel>()
        val state = viewModel.viewState

        val context = LocalContext.current



        LaunchedEffect(Unit) {
            viewModel.navigationEvent.collect { destination ->
                when (destination) {
                    "edit_profile" -> {
                        topLevelBackStack.add(EditProfile)
                    }
                }
            }
        }

        InitBroadcastReceiver(context)

        Scaffold(
            contentWindowInsets = WindowInsets(0.dp),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Profile")
                    },
                    actions = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            Modifier
                                .padding(end = 8.dp)
                                .clickable { viewModel.onEditProfileClicked()  }
                        )
                    }
                )
            }) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlideImage(
                    model = state.photoUri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(160.dp)
                        .padding(30.dp)
                        .clip(CircleShape)
                ) { request ->
                    request
                        .placeholder(R.drawable.character)
                        .error(R.drawable.character)
                }
                Text(text = getProfileFullName(state), style = MaterialTheme.typography.headlineLarge)
                Button(onClick = { handlerDownloadRequest(state.url, context) }) {
                    Text(text = "Download CV")
                }
                Spacer(Modifier.height(8.dp))

                var lessonTimeText : String
                lessonTimeText = if(state.lessonTime.isEmpty()){
                    "Lesson time hasn`t been set"
                } else {
                    "Lesson time - " + state.lessonTime
                }

                Text(lessonTimeText, style = MaterialTheme.typography.labelMedium)
            }
        }
    }

    @Composable
    private fun InitBroadcastReceiver(context: Context) {
        BroadcastReceiver(
            systemAction = DOWNLOAD_COMPLETE_ACTION,
            onSystemEvent = { intent ->
                if (intent?.action == DOWNLOAD_COMPLETE_ACTION) {
                    val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)
                    if (id != -1L) {
                        navigateToDownloadedInvoice(context)
                    }
                }
            })
    }

    private fun getProfileFullName(state : ProfileState) : String {
        var profileFullName = state.name
        if(!state.nick.isEmpty()){
            profileFullName += " - " + state.nick
        }
        return profileFullName
    }

    private fun handlerDownloadRequest(
        url: String,
        context: Context
    ) {
        val request: DownloadManager.Request = DownloadManager.Request(url.toUri())
        with(request) {
            setTitle("CV")
            setMimeType("pdf")
            setDescription("Downloading pdf...")
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "CV.pdf"
            )
        }
        val manager: DownloadManager =
            context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }

    private fun navigateToDownloadedInvoice(context: Context) {
        try {
            val file = File(
                Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS
                ),
                "CV.pdf"
            )
            val uri = FileProvider.getUriForFile(
                context,
                context.applicationContext?.packageName + ".provider",
                file
            )
            val intent =
                Intent(Intent.ACTION_VIEW)
            with(intent) {
                setDataAndType(
                    uri,
                    "application/pdf"
                )
                flags =
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object{
        private const val DOWNLOAD_COMPLETE_ACTION = "android.intent.action.DOWNLOAD_COMPLETE"
    }
}