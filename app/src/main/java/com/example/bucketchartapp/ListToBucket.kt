package com.example.bucketchartapp

object ListToBucket {
    /**
     * @param data
     * @return returns evenly distributed buckets such that each bucket sums closest to avgSum
     */
    fun divideIntoEvenlyBuckets(input: List<Int>, bucketSize: Int): List<List<Int>> {
        val totalSum = input.sum()
        val avgBucketSum = totalSum / (input.size / bucketSize)

        val buckets = mutableListOf<MutableList<Int>>()
        var currentBucket = mutableListOf<Int>()
        var currentSum = 0

        for (num in input) {
            if (currentSum + num <= avgBucketSum
                && currentBucket.size < bucketSize) {
                currentBucket.add(num)
                currentSum += num
            } else {
                if (currentBucket.isEmpty()) {
                    currentBucket.add(num)
                    currentSum = num
                } else {
                    buckets.add(currentBucket)
                    currentBucket = mutableListOf(num)
                    currentSum = num
                }
            }
        }

        // Add the last bucket if it's not empty
        if (currentBucket.isNotEmpty()) {
            buckets.add(currentBucket)
        }

        return buckets
    }
}





