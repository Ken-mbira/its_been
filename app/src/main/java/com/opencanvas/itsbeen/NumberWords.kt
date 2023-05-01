package com.opencanvas.itsbeen

import androidx.annotation.VisibleForTesting
import java.util.*

const val TAG = "NUMBER_WORDS"

@VisibleForTesting
class NumberWords {
    companion object {
        private val ones_array = listOf(
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",
            "twelve",
            "thirteen",
            "fourteen",
            "fifteen",
            "sixteen",
            "seventeen",
            "eighteen",
            "nineteen"
        )

        private val tens_array = listOf(
            "twenty",
            "thirty",
            "forty",
            "fifty",
            "sixty",
            "seventy",
            "eighty",
            "ninety"
        )

        private val hundreds_array = listOf(
            "hundred",
            "thousand",
            "million",
            "billion",
            "trillion",
            "quadrillion",
            "quintillion",
            "sextillion",
            "septillion",
            "octillion"
        )
    }

    fun convertToWords(number: String):String {
        return if(number != "0"){
            val commaSeparatedSections = commaSeparated(number)
            val numberSections = commaSeparatedSections.split(",")

            var sectionsInWords = ""

            for (i in numberSections.indices) {
                var focusSection = numberSections[i]
                while(focusSection.length < 3){
                    focusSection = "0$focusSection"
                }
                var newSection = sectionToWords(focusSection)
                val hundredsValue = (numberSections.count() - i) - 1
                if(hundredsValue != 0){
                    newSection += " ${hundreds_array[hundredsValue]}"
                    if(i < (numberSections.count() - 1)) {
                        val nextSection = numberSections[i + 1].toList().map{ it -> it.toString()}
                        val notZero = numberSections[i + 1].toList().map{ it -> it.toString()}.find { it -> it != "0" }
                        newSection += if(nextSection[0] == "0"){
                            if(notZero != null){
                                " and "
                            }else{""}
                        }else{
                            ", "
                        }
                    }
                }
                sectionsInWords += newSection
            }

            sectionsInWords.replaceFirstChar { it.titlecase(Locale.ROOT) }
        }else{
            ""
        }
    }

    private fun sectionToWords(number: String): String{
//        number needs to 3 in length
        if(number.length != 3 ){
            return ""
        }

        val segmentedNumber = number.toList().map { it -> it.toString() }
        var wordsString = ""

        if(segmentedNumber[0] != "0") {
            wordsString += ones_array[segmentedNumber[0].toInt() - 1]
            wordsString += " hundred"

            if(segmentedNumber[1] != "0" || segmentedNumber[2] != "0") {
                wordsString += " and "
            }
        }

        var isJoined = false

        if(segmentedNumber[1] != "0" && segmentedNumber[2] != "0"){
            val joined = "${segmentedNumber[1]}${segmentedNumber[2]}".toInt()
            if (joined <= 19) {
                isJoined = true
                wordsString += ones_array[joined - 1]
            }
        }

        if (!isJoined) {
            var hasMiddleNumber = false

            if(segmentedNumber[1] != "0") {
                val tensIndex = segmentedNumber[1].toInt() - 2
                wordsString += if(tensIndex < 0) {
                    "ten"
                }else{
                    tens_array[tensIndex]
                }
                hasMiddleNumber = true
            }

            if(segmentedNumber[2] != "0") {
                wordsString += "${if(hasMiddleNumber){" "}else{""}}${ones_array[segmentedNumber[2].toInt() - 1]}"
            }
        }

        return wordsString
    }

    fun commaSeparated(number: String):String {
        val fullWord = mutableListOf<String>()
        val wordCount = mutableListOf<String>()


        for (i in number.indices) {
            if(wordCount.count() == 3){
                fullWord.add(0, wordCount.joinToString(""))
                wordCount.clear()
                wordCount.add(number[number.count() - (i + 1)].toString())
            }else{
                wordCount.add(0, number[number.count() - (i + 1)].toString())
            }
        }
        fullWord.add(0, wordCount.joinToString(""))

        return fullWord.joinToString(",")
    }
}