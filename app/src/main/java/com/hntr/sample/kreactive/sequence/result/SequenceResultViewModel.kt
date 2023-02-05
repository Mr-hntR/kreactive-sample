package com.hntr.sample.kreactive.sequence.result

import androidx.lifecycle.*
import com.hntr.sample.domain.sequence.usecase.GetSequenceUseCase
import kotlinx.coroutines.launch

class SequenceResultViewModel(
    private val getSequenceUseCase: GetSequenceUseCase
) : ViewModel() {

    private val sequence = MutableLiveData<List<String>>()

    fun getSequence(): LiveData<List<String>> = sequence

    fun replaceSequence(
        firstMultiple: Int,
        secondMultiple: Int,
        firstWord: String,
        secondWord: String
    ) {
        viewModelScope.launch {
            sequence.value = getSequenceUseCase(firstMultiple, secondMultiple, firstWord, secondWord)
        }
    }
}