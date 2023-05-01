package com.opencanvas.itsbeen

import org.junit.Assert.assertEquals
import org.junit.Test

class NumberFormatterTests {
    @Test
    fun assert_comma_separated() {
        val valueInNumbers = "123444"
        val valueWithCommas = NumberWords().commaSeparated(valueInNumbers)
        val expectedValueWithCommas = "123,444"
        assertEquals(valueWithCommas, expectedValueWithCommas)
    }

    @Test
    fun assert_section_in_words() {
        val valueInNumbers = "123444"
        val valueInWords = NumberWords().convertToWords(valueInNumbers)
        val expectedValueInWords = "One hundred and twenty three thousand, four hundred and forty four"
        assertEquals(valueInWords, expectedValueInWords)
    }
}