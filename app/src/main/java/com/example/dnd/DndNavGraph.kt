package com.example.dnd

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dnd.ui.classes.ClassesScreen
import com.example.dnd.ui.classes.ClassesViewModel
import com.example.dnd.ui.features.FeaturesScreen
import com.example.dnd.ui.features.FeaturesViewModel
import com.example.dnd.ui.home.HomeScreen
import com.example.dnd.ui.monsters.MonstersScreen
import com.example.dnd.ui.monsters.MonstersViewModel
import com.example.dnd.ui.spells.SpellsScreen
import com.example.dnd.ui.spells.SpellsViewModel

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
            val classesViewModel = hiltViewModel<ClassesViewModel>()
            ClassesScreen(
                viewModel = classesViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(route = DndDestinations.FEATURES_ROUTE) {
            val featuresViewModel = hiltViewModel<FeaturesViewModel>()
            FeaturesScreen(
                viewModel = featuresViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(route = DndDestinations.MONSTERS_ROUTE) {
            val monstersViewModel = hiltViewModel<MonstersViewModel>()
            MonstersScreen(
                viewModel = monstersViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(route = DndDestinations.SPELLS_ROUTE) {
            val spellsViewModel = hiltViewModel<SpellsViewModel>()
            SpellsScreen(
                viewModel = spellsViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
