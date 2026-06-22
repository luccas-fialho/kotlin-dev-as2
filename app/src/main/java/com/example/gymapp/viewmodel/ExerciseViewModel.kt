package com.example.gymapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.gymapp.model.Exercise

class ExerciseViewModel: ViewModel() {

    private var nextId = 4

    var exercises = mutableStateListOf(
        Exercise(1, "Lat Pulldown", 12, 120),
        Exercise(2, "Bench Press", 10, 60),
        Exercise(3, "Squat", 8, 80),
    )
        private set

    fun addExercise(name: String, reps: Int, weight: Int) {
        exercises.add(
            Exercise(nextId, name, reps, weight)
        )

        nextId++
    }

    fun removeExercise(exercise: Exercise) {
        exercises.remove(exercise)
    }

    fun updateExercise(id: Int, newExercise: Exercise) {
        val index = exercises.indexOfFirst { it.id == id }

        if (index != -1) {
            exercises[index] = newExercise
        }
    }

    fun findExercise(id: Int): Exercise? {
        return exercises.find { it.id == id }
    }
}