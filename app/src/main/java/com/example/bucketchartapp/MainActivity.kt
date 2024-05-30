package com.example.bucketchartapp

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout.VERTICAL
import androidx.appcompat.app.AppCompatActivity
import com.example.bucketchartapp.Utils.generateRandomList

class MainActivity : AppCompatActivity() {
    lateinit var root: LinearLayout
    lateinit var bucketView: BucketView
    lateinit var shuffleBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        root = LinearLayout(this).apply {
            layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(Color.GRAY)
            orientation = VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
        }
        setContentView(root)

        bucketView = BucketView(this)
        bucketView.setBuckets(
            ListToBucket.divideIntoEvenlyBuckets(
                listOf(45, 85, 22, 34, 12, 20),
                2
            )
        )
        root.addView(bucketView)

        shuffleBtn = Button(this).apply {
            text = "SHUFFLE"
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            setOnClickListener(onShuffleListener)
        }
        root.addView(shuffleBtn)
    }

    val onShuffleListener = { view: View ->
        val list = generateRandomList(size = 6, min = 5, max = 45)
        bucketView.setBuckets(ListToBucket.divideIntoEvenlyBuckets(list, 3))
    }
}