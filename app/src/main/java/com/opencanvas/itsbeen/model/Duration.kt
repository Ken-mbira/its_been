package com.opencanvas.itsbeen.model


class Duration(val name: String, private val milliseconds: Long) {

    fun getNumerical(millisecondsAgo: Long):Int{
        return kotlin.math.ceil((millisecondsAgo / milliseconds).toDouble()).toInt()
    }

}