package com.wotosts.mygreens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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
        composable(Screens.ADD.name) {
        }
        composable(Screens.GARDEN.name) {
        }
    }
}

@Composable
private fun MainBottomNavigation(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation {
        Row {
            Screens.values().forEach {
                val isSelected = it.name == currentRoute
                BottomNavigationItem(
                    selected = isSelected,
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = it.icon),
                            contentDescription = it.name,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            text = it.displayName,
                            color = if(isSelected) Color.Black else Color.Black.copy(alpha = 0.3f),
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
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Black.copy(alpha = 0.3f)
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