package com.hntr.sample.domain.sequence.usecase

class GetSequenceUseCase {

    operator fun invoke(
        firstMultiple: Int,
        secondMultiple: Int,
        firstWord: String,
        secondWord: String
    ) = (LIMIT_MIN..LIMIT_MAX)
        .takeUnless { firstMultiple !in LIMIT_MIN..LIMIT_MAX || secondMultiple !in LIMIT_MIN..LIMIT_MAX }
        ?.map {
            when {
                it % firstMultiple == 0 && it % secondMultiple == 0 -> "$firstWord$secondWord"
                it % firstMultiple == 0 -> firstWord
                it % secondMultiple == 0 -> secondWord
                else -> it.toString()
            }
        } ?: defaultList

    companion object {
        const val LIMIT_MIN = 1
        const val LIMIT_MAX = 100

        val defaultList = (LIMIT_MIN..LIMIT_MAX).map { it.toString() }
    }
}