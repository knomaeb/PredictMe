package com.example.predictme.utils

fun isNameValid(name: String): Boolean {
    return name.matches("^[a-zA-Z]+$".toRegex())
}

fun validateNameOrThrow(name: String) {
    if (!isNameValid(name)) {
        throw IllegalArgumentException("Name must contain only letters.")
    }
}

fun handleNameSubmission(name: String) {
    try {
        validateNameOrThrow(name)
        println("Name '$name' is valid and submitted.")
    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}")
    }
}