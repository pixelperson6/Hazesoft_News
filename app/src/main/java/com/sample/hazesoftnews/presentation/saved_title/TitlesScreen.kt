package com.sample.hazesoftnews.presentation.saved_title

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sample.hazesoftnews.presentation.AppDrawer
import com.sample.hazesoftnews.presentation.Screen
import com.sample.hazesoftnews.presentation.saved_title.components.TitleItem
import kotlinx.coroutines.launch


@Composable
fun TitlesScreen(
    navController: NavController,
    viewModel: TitlesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var currentScreen by remember {
        mutableStateOf("Saved")
    }

    Scaffold(
       scaffoldState = scaffoldState,  topBar = {
            TopAppBar(
                title = { Text(text = "Saved titles") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch { scaffoldState.drawerState.open() }
                    }) {
                        Icon(Icons.Default.Menu, contentDescription = null)
                    }
                }
            )
        },

        drawerShape = RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp),
        drawerContent = {
            AppDrawer(
                currentScreen = currentScreen,
                navigateToHome = {
                    currentScreen = "Home"
                    navController.navigate(Screen.NewsListScreen.route) },
                navigateToSaved = {
                    currentScreen = "Saved"
                    navController.navigate(Screen.SavedTitleScreen.route)
                },
                closeDrawer = { scope.launch { scaffoldState.drawerState.close() } }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ){
                items(state){ title ->
                    TitleItem(
                        title = title,
                        modifier = Modifier
                            .fillMaxWidth(),
                        onDeleteClick = {
                            viewModel.onEvent(TitlesEvent.DeleteTitle(title))
                            scope.launch {
                               val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Title deleted",
                                   actionLabel = "Undo"
                                )
                                if (result == SnackbarResult.ActionPerformed){
                                    viewModel.onEvent(TitlesEvent.RestoreTitle)
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}