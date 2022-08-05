package com.sample.hazesoftnews.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sample.hazesoftnews.common.Constants.PARAM_INDEX
import com.sample.hazesoftnews.domain.model.Articles
import com.sample.hazesoftnews.presentation.news_details.NewsDetailScreen
import com.sample.hazesoftnews.presentation.news_list.NewsListScreen
import com.sample.hazesoftnews.presentation.saved_title.TitlesScreen
import com.sample.hazesoftnews.presentation.ui.theme.HazesoftNewsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    var articles: Articles? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HazesoftNewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()
                    val state = rememberScaffoldState()
                    val coroutineScope = rememberCoroutineScope()

                    var currentScreen by remember {
                        mutableStateOf("Home")
                    }

                    Surface {
                        Scaffold(
                            scaffoldState = state,
                            topBar = {
                                TopAppBar(
                                    title = { Text(text = "SoftHaze News") },
                                    navigationIcon = {
                                        IconButton(onClick = {
                                            coroutineScope.launch { state.drawerState.open() }
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
                                    closeDrawer = { coroutineScope.launch { state.drawerState.close() } }
                                )
                            },
                            content = {

                                NavHost(
                                    navController = navController,
                                    startDestination = Screen.NewsListScreen.route
                                ) {
                                    composable(
                                        route = Screen.NewsListScreen.route
                                    ) {
                                        NewsListScreen(navController = navController)
                                    }
                                    composable(
                                        route = Screen.NewsDetailScreen.route +
                                                "?$PARAM_INDEX={index}"
                                        ,
                                        arguments = listOf(
                                            navArgument(
                                                name = PARAM_INDEX
                                            ) {
                                                type = NavType.IntType
                                                defaultValue = 0
                                            }
                                        )
                                    ) {

                                        NewsDetailScreen(
                                            navController = navController
                                        )


                                    }
                                    composable(
                                        route = Screen.SavedTitleScreen.route
                                    ) {
                                        TitlesScreen(navController = navController)
                                    }

                                }
                            }
                        )
                    }


                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HazesoftNewsTheme {

    }
}
