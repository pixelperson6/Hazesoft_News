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
import com.sample.hazesoftnews.common.Constants.PARAM_AUTHOR
import com.sample.hazesoftnews.common.Constants.PARAM_CONTENT
import com.sample.hazesoftnews.common.Constants.PARAM_DECS
import com.sample.hazesoftnews.common.Constants.PARAM_IMAGE_URL
import com.sample.hazesoftnews.common.Constants.PARAM_INDEX
import com.sample.hazesoftnews.common.Constants.PARAM_PUBLISHED
import com.sample.hazesoftnews.common.Constants.PARAM_SOURCE_ID
import com.sample.hazesoftnews.common.Constants.PARAM_SOURCE_NAME
import com.sample.hazesoftnews.common.Constants.PARAM_TITLE
import com.sample.hazesoftnews.common.Constants.PARAM_URL
import com.sample.hazesoftnews.domain.model.Articles
import com.sample.hazesoftnews.presentation.news_details.NewsDetailScreen
import com.sample.hazesoftnews.presentation.news_list.NewsListScreen
import com.sample.hazesoftnews.presentation.ui.theme.HazesoftNewsTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    var articles :Articles? = null

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
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NewsListScreen.route
                    ){
                        composable(
                            route = Screen.NewsListScreen.route
                        ){
                            NewsListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.NewsDetailScreen.route+
                                    "?$PARAM_INDEX={index}"
                            /* +
                                    "&$PARAM_TITLE={title}" +
                                    "&$PARAM_AUTHOR={author}" +
                                    "&$PARAM_CONTENT={content}" +
                                    "&$PARAM_DECS={desc}" +
                                    "&$PARAM_SOURCE_NAME={name}" +
                                    "&$PARAM_PUBLISHED={published}" +
                                    "&$PARAM_URL={url}" +
                                    "&$PARAM_IMAGE_URL={image_url}"*/,
                            arguments = listOf(
                                navArgument(
                                    name = PARAM_INDEX
                                ){
                                    type= NavType.IntType
                                    defaultValue = 0
                                }
                            )
                            /*

                            arguments = listOf(
                                navArgument(
                                    name = PARAM_SOURCE_ID
                                ){
                                    type= NavType.StringType
                                    defaultValue = ""
                                },
                                navArgument(
                                    name = PARAM_TITLE
                                ){
                                    type= NavType.StringType
                                    defaultValue = ""
                                },
                                navArgument(
                                    name = PARAM_AUTHOR
                                ){
                                    type= NavType.StringType
                                    defaultValue = ""
                                },
                                navArgument(
                                    name = PARAM_CONTENT
                                ){
                                    type= NavType.StringType
                                    defaultValue = ""
                                },
                                navArgument(
                                    name = PARAM_DECS
                                ){
                                    type= NavType.StringType
                                    defaultValue = ""
                                },
                                navArgument(
                                    name = PARAM_SOURCE_NAME
                                ){
                                    type= NavType.StringType
                                    defaultValue = ""
                                },
                                navArgument(
                                    name = PARAM_PUBLISHED
                                ){
                                    type= NavType.StringType
                                    defaultValue = ""
                                },
                                navArgument(
                                    name = PARAM_URL
                                ){
                                    type= NavType.StringType
                                    defaultValue = ""
                                },
                                navArgument(
                                    name = PARAM_IMAGE_URL
                                ){
                                    type= NavType.StringType
                                    defaultValue = ""
                                }
                            )*/
                        ){

                            NewsDetailScreen(
                                navController = navController
                            )



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
