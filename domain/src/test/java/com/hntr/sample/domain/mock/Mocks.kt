package com.hntr.sample.domain.mock

const val firstWord = "firstWord"
const val secondWord = "secondWord"

val defaultList = (1..100)
    .map { it.toString() }

val listWithMultiples99And100 = (1..98)
    .map { it.toString() }
    .toMutableList()
    .apply {
        add(firstWord)
        add(secondWord)
    }

val listWithSameWords = (1..98)
    .map { it.toString() }
    .toMutableList()
    .apply {
        add(firstWord)
        add(firstWord)
    }

val listWithSameMultiples1 = (1..100)
    .map { "$firstWord$secondWord" }