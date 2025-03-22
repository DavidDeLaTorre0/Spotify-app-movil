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
fun SearchSongsScreen(viewModel: SongsViewModel = viewModel()) {
    var searchQuery by remember { mutableStateOf("") }
    var isSearchVisible by remember { mutableStateOf(false) }
    var hasSearched by remember { mutableStateOf(false) }
    val songs by viewModel.songs.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(true)
    val error by viewModel.error.observeAsState()

    val filteredSongs = songs.filter { it.info.title.contains(searchQuery, ignoreCase = true) }

    Scaffold(
        topBar = { TopBarSeach(isSearchVisible, searchQuery, onSearchQueryChange = {
            searchQuery = it
            hasSearched = it.isNotEmpty()
        }) },
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

                    hasSearched -> {
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
fun TopBarSeach(isSearchVisible: Boolean, searchQuery: String, onSearchQueryChange: (String) -> Unit) {
    TopAppBar(
        title = {
            TextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                label = { Text("Buscar") },
                modifier = Modifier.fillMaxWidth()
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
    )

}



