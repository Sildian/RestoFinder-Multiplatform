package com.sildian.apps.restofinder.uilayer

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.floor
import kotlin.random.Random

private typealias Index = Int

internal fun interface AutomaticIndexSelectorFlow<T> {

    @Throws(IllegalArgumentException::class, CancellationException::class)
    suspend operator fun invoke(items: List<T>): Flow<Index>

    fun interface IndexSelectorFunction<T> {
        @Throws(IllegalArgumentException::class)
        operator fun invoke(items: List<T>): Index
    }
}

internal class RollingIndexSelectorFlow<T>(
    private val indexSelectorFunction: AutomaticIndexSelectorFlow.IndexSelectorFunction<T>,
    private val delayBetweenEachItemInMillis: Long,
): AutomaticIndexSelectorFlow<T> {

    @Throws(IllegalArgumentException::class, CancellationException::class)
    override suspend operator fun invoke(items: List<T>): Flow<Index> = flow {
        val selectedIndexOffset = indexSelectorFunction(items)
        var currentIndexOffset = 0
        var currentIndex = 0
        emit(currentIndex)
        while (currentIndexOffset < selectedIndexOffset) {
            delay(delayBetweenEachItemInMillis)
            currentIndexOffset++
            currentIndex = (currentIndexOffset - floor(currentIndexOffset.toDouble() / items.size.toDouble()) * items.size).toInt()
            emit(currentIndex)
        }
    }
}

internal class RandomIndexSelectorFunction<T>(
    private val maxItemsBrowseCount: Int,
) : AutomaticIndexSelectorFlow.IndexSelectorFunction<T> {

    @Throws(IllegalArgumentException::class)
    override fun invoke(items: List<T>): Index {
        if (maxItemsBrowseCount <= 0) {
            throw IllegalArgumentException("The max items browse count should be positive.")
        }
        if (items.isEmpty()) {
            throw IllegalArgumentException("The provided list should not be empty.")
        }
        return Random.nextInt(
            from = 0,
            until = items.size * maxItemsBrowseCount,
        )
    }
}