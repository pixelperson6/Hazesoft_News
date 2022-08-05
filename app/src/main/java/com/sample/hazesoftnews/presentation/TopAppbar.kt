package com.sample.hazesoftnews.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import com.sample.hazesoftnews.R
import com.sample.hazesoftnews.presentation.news_list.NewsListViewModel
import com.sample.hazesoftnews.presentation.util.SearchBarState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun TopAppBar(
    viewModel: NewsListViewModel = hiltViewModel(),
    searchBarState: SearchBarState,
    searchTextState: String,
    state:ScaffoldState,
    scope:CoroutineScope
) {

    when (searchBarState) {
        SearchBarState.CLOSED -> {
            DefaultAppBar(
                onSearchClicked = {
                    viewModel.searchBarState.value = SearchBarState.OPENED
                },state,scope
            )
        }
        else -> {
            SearchTopBar(
                text = searchTextState,
                onTextChange = { newText ->
                    viewModel.searchTextState.value = newText
                },
                onCancelClicked = {
                    viewModel.searchBarState.value = SearchBarState.CLOSED
                    viewModel.searchTextState.value = ""
                    viewModel.getNews()

                },
                onSearchClicked = {
                    viewModel.getNewsByQuery(it)
                }
            )

        }

    }
}


@Composable
fun DefaultAppBar(
    onSearchClicked: () -> Unit,
    state:ScaffoldState,
    scope:CoroutineScope
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colors.onSurface
            )
        },navigationIcon = {
            IconButton(onClick = {
                scope.launch { state.drawerState.open() }
            }) {
                Icon(Icons.Default.Menu, contentDescription = null)
            }
        },
        actions = {
            SearchAction(onSearchClicked = onSearchClicked)
        },
        backgroundColor = MaterialTheme.colors.surface

    )
}


@Composable
fun SearchAction(onSearchClicked: () -> Unit) {
    IconButton(onClick = { onSearchClicked() }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(id = R.string.search_news),
            tint = MaterialTheme.colors.onSurface
        )
    }
}





@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTopBar(
    text: String,
    onTextChange: (value: String) -> Unit,
    onCancelClicked: () -> Unit,
    onSearchClicked: (stringQuery: String) -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.surface
    ) {

        val keyboardController = LocalSoftwareKeyboardController.current
        val focusManager = LocalFocusManager.current

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = stringResource(R.string.search),
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.onSurface,
                fontSize = MaterialTheme.typography.subtitle1.fontSize

            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.disabled),
                    onClick = { }) {

                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_icon),
                        tint = MaterialTheme.colors.onSurface
                    )

                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if(text.isNotEmpty()){
                            onTextChange("")
                        }else{
                            onCancelClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close_icon),
                        tint = MaterialTheme.colors.onSurface
                    )

                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.onSurface,
                focusedIndicatorColor = Color.Transparent,
                disabledPlaceholderColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent

            )


        )


    }


}