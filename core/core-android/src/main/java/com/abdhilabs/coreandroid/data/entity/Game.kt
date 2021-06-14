package com.abdhilabs.coreandroid.data.entity

data class Game(
    val id: Int,
    val name: String,
    val description: String,
    val released: String,
    val backgroundImage: String,
    val rating: Double,
    val genres: List<String>
)
