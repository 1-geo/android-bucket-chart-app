package com.example.bucketchartapp

import android.content.res.Resources
import android.graphics.Color
import android.util.TypedValue
import kotlin.random.Random

object Utils {

    // dp to px
    fun Int.dpToPx(resources: Resources): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            resources.displayMetrics
        ).toInt()
    }

    // Function to generate a random color
    fun getRandomColor(): Int {
        val random = Random.Default
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }

    // generate a random list with numbers in range
    fun generateRandomList(size: Int, min: Int, max: Int): List<Int> {
        val random = Random.Default
        return List(size) { random.nextInt(min, max + 1) }
    }
}