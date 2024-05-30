package com.example.bucketchartapp

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.example.bucketchartapp.Utils.dpToPx
import com.example.bucketchartapp.Utils.getRandomColor

class BucketView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val margin: Int = 8.dpToPx(context.resources)
    private val parentHeight: Int = 256.dpToPx(context.resources)
    val parentWidth = resources.displayMetrics.widthPixels - 2 * margin

    init {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            parentHeight
        ).apply {
            setMargins(margin, margin, margin, margin)
        }
    }

    fun setBuckets(buckets: List<List<Int>>) {
        removeAllViews()

        // Calculate total sum of all buckets
        val totalSum = buckets.sumBy { it.sum() }.toFloat()

        // Initialize the starting position for placing views
        var currentLeft = 0

        // Add LinearLayouts for each bucket with sizes proportional to their sums
        buckets.forEach { bucket ->
            val bucketSum = bucket.sum().toFloat()
            val weight = bucketSum / totalSum

            // Calculate width based on proportion and parent width
            var width = (parentWidth * weight).toInt()
            val height = parentHeight

            // Adjust width if it overflows the current row
            if (currentLeft + width > parentWidth) {
                width = parentWidth - currentLeft
            }

            // Create a LinearLayout for the bucket
            val linearLayout = LinearLayout(context).apply {
                layoutParams = LayoutParams(width, height).apply {
                    leftMargin = currentLeft
                }
                orientation = LinearLayout.VERTICAL
                setBackgroundColor(getRandomColor())
            }

            // Textview will get weight based on its value/bucketSum
            bucket.forEach { element ->
                val textView = TextView(context).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        0,
                        element.toFloat() / bucketSum
                    )

                    // styling
                    text = element.toString()
                    setTextColor(Color.BLACK)
                    gravity = Gravity.CENTER
                    setBackgroundColor(getRandomColor())
                    setTextColor(Color.WHITE)
                    textSize = 16f
                    setTypeface(null, Typeface.BOLD);
                }
                linearLayout.addView(textView)
            }

            addView(linearLayout)
            // Update the position for the next view
            currentLeft += width
        }
    }
}