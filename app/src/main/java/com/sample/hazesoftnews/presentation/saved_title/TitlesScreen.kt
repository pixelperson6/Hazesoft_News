package com.sample.hazesoftnews.presentation.saved_title

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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

    Scaffold(
       scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Saved titles",
                    style = MaterialTheme.typography.h4
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
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