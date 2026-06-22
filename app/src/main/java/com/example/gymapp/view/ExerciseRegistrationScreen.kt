package com.example.gymapp.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.gymapp.model.Exercise
import com.example.gymapp.viewmodel.ExerciseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseRegistrationScreen(
    exerciseViewModel: ExerciseViewModel,
    exerciseId: Int?,
    onNavigateBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var reps by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    val isEditing = exerciseId != null

    LaunchedEffect(exerciseId) {
        if (isEditing) {
            val exercise = exerciseViewModel.exercises.find { it.id == exerciseId }
            if (exercise != null) {
                name = exercise.exerciseName
                reps = exercise.reps.toString()
                weight = exercise.weight.toString()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isEditing) "Edit Exercise" else "New Exercise",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Exercise Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = MaterialTheme.shapes.medium
            )

            OutlinedTextField(
                value = reps,
                onValueChange = { reps = it },
                label = { Text("Repetitions") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                shape = MaterialTheme.shapes.medium
            )

            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text("Weight (kg)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                shape = MaterialTheme.shapes.medium
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    val repsInt = reps.toIntOrNull() ?: 0
                    val weightInt = weight.toIntOrNull() ?: 0

                    if (name.isNotBlank()) {
                        if (isEditing && exerciseId != null) {
                            val updatedExercise = Exercise(
                                id = exerciseId,
                                exerciseName = name,
                                reps = repsInt,
                                weight = weightInt
                            )
                            exerciseViewModel.updateExercise(updatedExercise)
                        } else {
                            val newExercise = Exercise(
                                exerciseName = name,
                                reps = repsInt,
                                weight = weightInt
                            )
                            exerciseViewModel.addExercise(newExercise)
                        }
                        onNavigateBack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = MaterialTheme.shapes.medium,
                enabled = name.isNotBlank()
            ) {
                Text(
                    text = "Save Exercise",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}