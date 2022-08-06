package com.sample.hazesoftnews.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sample.hazesoftnews.common.Constants.PARAM_INDEX
import com.sample.hazesoftnews.domain.model.Articles
import com.sample.hazesoftnews.presentation.news_details.components.DetailScreen
import com.sample.hazesoftnews.presentation.news_list.NewsListScreen
import com.sample.hazesoftnews.presentation.saved_title.TitlesScreen
import com.sample.hazesoftnews.presentation.ui.theme.HazesoftNewsTheme
import dagger.hilt.android.AndroidEntryPoint


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

                    Surface {


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
                                        "?$PARAM_INDEX={index}",
                                arguments = listOf(
                                    navArgument(
                                        name = PARAM_INDEX
                                    ) {
                                        type = NavType.IntType
                                        defaultValue = 0
                                    }
                                )
                            ) {

                                DetailScreen(navController = navController)


                            }
                            composable(
                                route = Screen.SavedTitleScreen.route
                            ) {
                                TitlesScreen(navController = navController)
                            }

                        }
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
