package com.example.secondassessment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var input = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        var eleToSearch = 9
        val pos = binarySearchRecursive(input, eleToSearch, 0, input.size - 1)
        if (pos >= 0) {
           Toast.makeText(applicationContext,"Element found at  "+pos,Toast.LENGTH_SHORT).show()
            println(pos) // to print position at last
        } else {
           Toast.makeText(applicationContext,"Element not found",Toast.LENGTH_SHORT).show()
        }

    }

    fun binarySearchRecursive(input: Array<Int>, eleToSearch: Int, low:Int, high:Int): Int {

        while(low <=high) {
            val mid = (low + high) /2
            when {
                eleToSearch > input[mid] -> return binarySearchRecursive(input, eleToSearch, mid+1, high)   // element is greater than middle element of array, so it will be in right half. Recursion will call the right half again
                eleToSearch < input[mid] -> return binarySearchRecursive(input, eleToSearch, low, mid-1)    //element is less than middle element of array, so it will be in left half of the array. Recursion will call the left half again.
                eleToSearch == input[mid] -> return mid // element found.
            }
        }
        return -1
    }
}