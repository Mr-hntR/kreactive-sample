package com.hntr.sample.kreactive.di

import com.hntr.sample.domain.sequence.usecase.GetSequenceUseCase
import com.hntr.sample.kreactive.sequence.result.SequenceResultViewModel

object ViewModelFactory {

    fun createSequenceResultViewModel() = SequenceResultViewModel(getSequenceUseCase = GetSequenceUseCase())
}