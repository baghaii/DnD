package com.example.dnd

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dnd.ui.home.ClassesScreen
import com.example.dnd.ui.home.FeaturesScreen
import com.example.dnd.ui.home.HomeScreen
import com.example.dnd.ui.home.MonstersScreen
import com.example.dnd.ui.home.SpellsScreen

@Composable
fun DndNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = DndDestinations.HOME_ROUTE,
    ) {
        composable(route = DndDestinations.HOME_ROUTE) {
            HomeScreen(
                onClassesClick = { navController.navigate(DndDestinations.CLASSES_ROUTE) },
                onFeaturesClick = { navController.navigate(DndDestinations.FEATURES_ROUTE) },
                onMonstersClick = { navController.navigate(DndDestinations.MONSTERS_ROUTE) },
                onSpellsClick = { navController.navigate(DndDestinations.SPELLS_ROUTE) },
                modifier = modifier
            )
        }
        composable(route = DndDestinations.CLASSES_ROUTE) {
            ClassesScreen(
                onBackClick = { navController.popBackStack() },
                modifier = modifier)
        }
        composable(route = DndDestinations.FEATURES_ROUTE) {
            FeaturesScreen(modifier = modifier)
        }
        composable(route = DndDestinations.MONSTERS_ROUTE) {
            MonstersScreen(modifier = modifier)
        }
        composable(route = DndDestinations.SPELLS_ROUTE) {
            SpellsScreen(modifier = modifier)
        }
    }
}
