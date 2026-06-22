package com.example.gymapp.view

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gymapp.viewmodel.ExerciseViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val exerciseViewModel: ExerciseViewModel = viewModel()

    NavHost(
        navController, "list"
    ) {
        composable("list") {
            ExerciseListScreen(
                exerciseViewModel = exerciseViewModel,
                onNavigateToRegistration = {
                    navController.navigate("registration")
                },
                onUpdateExercise = { id ->
                    navController.navigate("registration/$id")
                }
            )
        }

        composable("registration") {
            ExerciseRegistrationScreen(
                exerciseViewModel,
                exerciseId = null,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("registration/{exerciseId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("exerciseId")?.toIntOrNull()

            ExerciseRegistrationScreen(
                exerciseViewModel,
                id,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}