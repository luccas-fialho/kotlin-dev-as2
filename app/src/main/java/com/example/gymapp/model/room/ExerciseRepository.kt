package com.example.gymapp.model.room

import com.example.gymapp.model.Exercise
import kotlinx.coroutines.flow.Flow

class ExerciseRepository(
    private val exerciseDao: ExerciseDao
) {
    fun listExercises(): Flow<List<Exercise>> {
        return exerciseDao.listExercises()
    }

    suspend fun addExercise(name: String, reps: Int, weight: Int) {
        exerciseDao.addExercise(
            Exercise(exerciseName = name, reps = reps, weight = weight)
        )
    }

    suspend fun removeExercise(exercise: Exercise) {
        exerciseDao.removeExercise(exercise)
    }

    suspend fun updateExercise(exercise: Exercise) {
        exerciseDao.updateExercise(exercise)
    }

    suspend fun findExerciseById(id: Int): Exercise? {
        return exerciseDao.findExerciseById(id)
    }
}