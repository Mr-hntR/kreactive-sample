package com.hntr.sample.domain.sequence.usecase

import com.hntr.sample.domain.mock.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class GetSequenceUseCaseTest {

    private val getSequenceUseCase = GetSequenceUseCase()

    @Test
    fun `calling use case with first multiple off limits should return default sequence`() = runTest {
        val sequence = getSequenceUseCase(
            firstMultiple = 0,
            secondMultiple = 100,
            firstWord = firstWord,
            secondWord = secondWord
        )
        assertEquals(defaultList, sequence)
    }

    @Test
    fun `calling use case with second multiple off limits should return default sequence`() = runTest {
        val sequence = getSequenceUseCase(
            firstMultiple = 1,
            secondMultiple = 101,
            firstWord = firstWord,
            secondWord = secondWord
        )
        assertEquals(defaultList, sequence)
    }

    @Test
    fun `calling use case with both multiples off limits should return default sequence`() = runTest {
        val sequence = getSequenceUseCase(
            firstMultiple = 0,
            secondMultiple = 101,
            firstWord = firstWord,
            secondWord = secondWord
        )
        assertEquals(defaultList, sequence)
    }

    @Test
    fun `calling use case with 2 distinct multiples should be replaced in sequence`() = runTest {
        val sequence = getSequenceUseCase(
            firstMultiple = 99,
            secondMultiple = 100,
            firstWord = firstWord,
            secondWord = secondWord
        )
        assertEquals(listWithMultiples99And100, sequence)
    }

    @Test
    fun `calling use case with same multiple should be concatenate in sequence`() = runTest {
        val sequence = getSequenceUseCase(
            firstMultiple = 1,
            secondMultiple = 1,
            firstWord = firstWord,
            secondWord = secondWord
        )
        assertEquals(listWithSameMultiples1, sequence)
    }

    @Test
    fun `calling use case with same words should show the same word in sequence`() = runTest {
        val sequence = getSequenceUseCase(
            firstMultiple = 99,
            secondMultiple = 100,
            firstWord = firstWord,
            secondWord = firstWord
        )
        assertEquals(listWithSameWords, sequence)
    }
}