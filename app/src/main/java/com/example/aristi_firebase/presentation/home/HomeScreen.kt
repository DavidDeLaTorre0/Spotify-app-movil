package com.example.aristi_firebase.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.aristi_firebase.R
import com.example.aristi_firebase.viewmodel.SongsViewModel

@Composable
fun HomeScreen(viewModel: SongsViewModel = viewModel()) {
    var searchQuery by remember { mutableStateOf("") }
    var isSearchVisible by remember { mutableStateOf(false) }
    val songs by viewModel.songs.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(true)
    val error by viewModel.error.observeAsState()

    val filteredSongs = songs.filter { it.info.title.startsWith(searchQuery, ignoreCase = true) }

    Scaffold(
        topBar = { TopBar(isSearchVisible, searchQuery, onSearchQueryChange = { searchQuery = it }) },
        bottomBar = { BottomBar { isSearchVisible = !isSearchVisible } },
        containerColor = colorResource(id = R.color.custom_green),
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

                    isSearchVisible && searchQuery.isEmpty() -> {

                    }

                    else -> {
                        LazyColumn {
                            items(filteredSongs) { song ->
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
fun TopBar(isSearchVisible: Boolean, searchQuery: String, onSearchQueryChange: (String) -> Unit) {
    TopAppBar(
        title = {
            if (isSearchVisible) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    TextField(
                        value = searchQuery,
                        onValueChange = onSearchQueryChange,
                        label = { Text("Buscar") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    )
                }
            } else {
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
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
    )
}


@Composable
fun BottomBar(onSearchClick: () -> Unit) {
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
            label = {
                Text(
                    stringResource(id = R.string.home_home_screen),
                    color = Color.White,
                    fontSize = 12.sp
                )
            },
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
            label = {
                Text(
                    stringResource(id = R.string.search_home),
                    color = Color.White,
                    fontSize = 12.sp
                )
            },
            selected = false,
            onClick = onSearchClick
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