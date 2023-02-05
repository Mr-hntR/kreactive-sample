package com.hntr.sample.kreactive.navigation

object NavParamFactory {

    fun createSequenceResultNavParams(
        firstMultiplePicker: Int,
        secondMultiplePicker: Int,
        firstWord: String,
        secondWord: String
    ) = SequenceResultNavParams(
        firstMultipleValue = firstMultiplePicker,
        secondMultipleValue = secondMultiplePicker,
        firstWordValue = firstWord,
        secondWordValue = secondWord
    )
}
