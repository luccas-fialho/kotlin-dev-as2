package com.example.gymapp.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.model.Exercise
import com.example.gymapp.model.room.AppDatabase
import com.example.gymapp.model.room.ExerciseRepository
import kotlinx.coroutines.launch

class ExerciseViewModel(application: Application): AndroidViewModel(application) {

    private val repository: ExerciseRepository

    var exercises = mutableStateListOf<Exercise>()
        private set

    init {
        val dao = AppDatabase.getDatabase(application).exerciseDao()
        repository = ExerciseRepository(dao)
        loadExercises()
    }

    private fun loadExercises() {
        viewModelScope.launch {
            repository.listExercises().collect { list ->
                exercises.clear()
                exercises.addAll(list)
            }
        }
    }

    fun addExercise(exercise: Exercise) {
        viewModelScope.launch {
            repository.addExercise(exercise)
        }
    }

    fun removeExercise(exercise: Exercise) {
        viewModelScope.launch {
            repository.removeExercise(exercise)
        }
    }

    fun updateExercise(exercise: Exercise) {
        viewModelScope.launch {
            repository.updateExercise(exercise)
        }
    }

    fun findExercise(id: Int): Exercise? {
        return exercises.find { it.id == id }
    }
}