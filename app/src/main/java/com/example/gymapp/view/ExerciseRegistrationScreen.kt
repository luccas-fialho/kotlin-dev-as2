package com.example.gymapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gymapp.model.Exercise
import com.example.gymapp.viewmodel.ExerciseViewModel

@Composable
fun ExerciseRegistrationScreen(
    exerciseViewModel: ExerciseViewModel,
    exerciseId: Int?,
    onNavigateBack: () -> Unit
) {
    val existentExercise = exerciseId?.let {
        exerciseViewModel.findExercise(it)
    }

    var name by remember {
        mutableStateOf(existentExercise?.exerciseName ?: "")
    }

    var repsText by remember {
        mutableStateOf(existentExercise?.reps?.toString() ?: "")
    }

    var weightText by remember {
        mutableStateOf(existentExercise?.weight?.toString() ?: "")
    }

    var title = if (exerciseId == null) {
        "Product Registration"
    }
    else {
        "Product Update"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(title, style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            name,
            onValueChange = { name = it },
            label = { Text("Exercise Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            repsText,
            onValueChange = { repsText = it },
            label = { Text("Reps") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            weightText,
            onValueChange = { weightText = it },
            label = { Text("Weight") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val reps = repsText.toIntOrNull() ?: 0
                val weight = weightText.toIntOrNull() ?: 0

                if (exerciseId == null) {
                    exerciseViewModel.addExercise(name, reps, weight)
                }
                else {
                    val newExercise = Exercise(exerciseId, name, reps, weight)
                    exerciseViewModel.updateExercise(exerciseId, newExercise)
                }

                onNavigateBack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = onNavigateBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }
}