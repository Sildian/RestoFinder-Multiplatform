package com.sildian.apps.restofinder.core.testutils

import kotlin.random.Random

private const val RANDOM_STRING_MAX_SIZE = 20

private val lowerCaseAlphaChars: CharRange get() = ('a'..'z')
private val upperCaseAlphaChars: CharRange get() = ('A'..'Z')
private val numericalChars: CharRange get() = ('0'..'9')

@TestOnly
fun Random.nextString(
    size: Int = nextInt(until = RANDOM_STRING_MAX_SIZE),
): String {
    val availableChars = lowerCaseAlphaChars + upperCaseAlphaChars + numericalChars
    return nextString(size = size, availableChars = availableChars)
}

@TestOnly
fun Random.nextAlphaString(
    size: Int = nextInt(until = RANDOM_STRING_MAX_SIZE),
): String {
    val availableChars = lowerCaseAlphaChars + upperCaseAlphaChars
    return nextString(size = size, availableChars = availableChars)
}

@TestOnly
fun Random.nextAlphaLowerCaseString(
    size: Int = nextInt(until = RANDOM_STRING_MAX_SIZE),
): String {
    val availableChars = lowerCaseAlphaChars.toList()
    return nextString(size = size, availableChars = availableChars)
}

@TestOnly
fun Random.nextAlphaUpperCaseString(
    size: Int = nextInt(until = RANDOM_STRING_MAX_SIZE),
): String {
    val availableChars = upperCaseAlphaChars.toList()
    return nextString(size = size, availableChars = availableChars)
}

@TestOnly
fun Random.nextNumericalString(
    size: Int = nextInt(until = RANDOM_STRING_MAX_SIZE),
): String {
    val availableChars = numericalChars.toList()
    return nextString(size = size, availableChars = availableChars)
}

@TestOnly
fun Random.nextUrlString(): String {
    val scheme: String = "https"
    val domainName: String = nextAlphaLowerCaseString()
    return "$scheme://$domainName.com/"
}

@TestOnly
fun Random.nextEmailAddressString(): String {
    val accountName: String = nextAlphaLowerCaseString()
    val domainName: String = nextAlphaLowerCaseString(size = 5)
    return "$accountName@$domainName.com"
}

@TestOnly
private fun Random.nextString(
    size: Int,
    availableChars: List<Char>,
): String {
    return List(size = size) {
        availableChars.random()
    }.joinToString(separator = "") { it.toString() }
}