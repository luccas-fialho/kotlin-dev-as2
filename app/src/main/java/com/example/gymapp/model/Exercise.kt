package com.example.gymapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val exerciseName: String,
    val reps: Int,
    val weight: Int
)