package com.wotosts.mygreens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wotosts.mygreens.home.HomeScreen
import com.wotosts.mygreens.ui.theme.MyGreensTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    MyGreensTheme {
        val navController = rememberNavController()
        Scaffold(bottomBar = { MainBottomNavigation(navController = navController) }) {
            MainNavHost(navController = navController, modifier = Modifier.padding(it))
        }
    }
}

@Composable
private fun MainNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screens.HOME.name,
        modifier = modifier
    ) {
        composable(Screens.HOME.name) {
            HomeScreen()
        }
    }
}

@Composable
private fun MainBottomNavigation(navController: NavHostController) {
    BottomNavigation {
        Row {
            Screens.values().forEach {
                BottomNavigationItem(
                    selected = it == Screens.HOME,
                    icon = {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = it.name
                        )
                    },
                    label = {
                        Text(
                            text = it.displayName,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    },
                    onClick = {
                        navController.navigate(it.name) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier.wrapContentSize(align = Alignment.Center),
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.White.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyGreensTheme {
        MainScreen()
    }
}