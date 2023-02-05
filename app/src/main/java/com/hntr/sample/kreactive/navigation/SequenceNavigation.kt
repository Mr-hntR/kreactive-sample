package com.hntr.sample.kreactive.navigation

const val FIRST_MULTIPLE = "firstMultiple"
const val SECOND_MULTIPLE = "secondMultiple"
const val FIRST_WORD = "firstWord"
const val SECOND_WORD = "secondWord"

data class SequenceResultNavParams(
    val firstMultipleValue: Int,
    val secondMultipleValue: Int,
    val firstWordValue: String,
    val secondWordValue: String
)