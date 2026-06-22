package com.example.gymapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gymapp.model.Exercise
import com.example.gymapp.viewmodel.ExerciseViewModel

@Composable
fun ExerciseListScreen (
    exerciseViewModel: ExerciseViewModel,
    onNewExercise: () -> Unit,
    onUpdateExercise: (Int) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNewExercise
            ) {
                Text("+")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Workout Program",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(exerciseViewModel.exercises) { exercise ->
                    ExerciseItem(
                        exercise,
                        onUpdate = {
                            onUpdateExercise(exercise.id)
                        },
                        onRemove = {
                            exerciseViewModel.removeExercise(exercise)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ExerciseItem (
    exercise: Exercise,
    onUpdate: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                exercise.exerciseName,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                "Reps: ${exercise.reps}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                "Weight: ${exercise.weight} Kg",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(8.dp))

            Row {
                Button(
                    onClick = onUpdate
                ) {
                    Text("Update")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = onRemove,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Remove")
                }
            }
        }
    }
}