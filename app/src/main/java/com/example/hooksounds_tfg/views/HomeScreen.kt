package com.example.hooksounds_tfg.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.hooksounds_tfg.viewmodel.SongsViewModel
import com.example.hooksounds_tfg.R


@Composable
fun HomeScreen(viewModel: SongsViewModel = viewModel()) {
    val songs by viewModel.songs.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(true)
    val error by viewModel.error.observeAsState()
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar() },
        containerColor = Color(0xFFBDBDBD),
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                when {
                    isLoading -> {
                        CircularProgressIndicator()
                    }
                    error != null -> {
                        Text(text = "Error: $error", color = MaterialTheme.colorScheme.error)
                    }
                    else -> {
                        LazyColumn {
                            items(songs) { song ->
                                SongItem(
                                    title = song.info.title,
                                    thumbnailUrl = song.info.thumbnail
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = stringResource(id = R.string.profile_home),
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.profile_home),
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }

                TextButton(
                    onClick = { /* Acción del filtro */ },
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.Green)
                ) {
                    Text(text = stringResource(id = R.string.home_home_screen), fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.width(48.dp))
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
    )
}

@Composable
fun BottomBar() {
    NavigationBar(
        containerColor = Color.Black
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = stringResource(id = R.string.home_home_screen),
                    tint = Color.White
                )
            },
            label = { Text(stringResource(id = R.string.home_home_screen), color = Color.White, fontSize = 12.sp) },
            selected = false,
            onClick = { /* Acción al hacer clic */ }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_home),
                    tint = Color.White
                )
            },
            label = { Text(stringResource(id = R.string.search_home), color = Color.White, fontSize = 12.sp) },
            selected = false,
            onClick = { /* Acción al hacer clic */ }
        )
    }
}
@Composable
fun SongItem(title: String, thumbnailUrl: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val image = rememberAsyncImagePainter(thumbnailUrl)
            Image(
                painter = image,
                contentDescription = "Song Thumbnail",
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(title, style = MaterialTheme.typography.titleLarge)
        }
    }
}