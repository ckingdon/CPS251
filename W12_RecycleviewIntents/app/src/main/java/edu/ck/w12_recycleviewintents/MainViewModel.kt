package edu.ck.w12_recycleviewintents


import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.*


class MainViewModel: ViewModel() {

    private val TAG = "MainViewModelX"

    // empty array to hold randomized data arrays
    //var save2dArr: Array<Array<String>> = arrayOf(arrayOf<String>()) // inefficient because first "row" is empty
    var save2dArr: Array<Array<String>> = emptyArray() // better here

    fun randomizeArray(array: Array<String>): Array<String>{
        array.shuffle()                 // shuffles array elements in place ...
        val saveArr = array.copyOf()    // so a copy is made ...
        this.save2dArr += saveArr       // and then add to the 2D array that holds all the randomized arrays

        val fName = object{}.javaClass.enclosingMethod.name
        Log.i(TAG,"###### $fName: " + Arrays.toString(saveArr) + "#######")

        return array                    // shuffled array is returned
    }



    /*
    // not using, but good function to get random integer
    // return a random number in range min to max
    fun randomInt(min: Int, max: Int): Int {
        var r = Random.nextInt(min,max)
        //val fName = object{}.javaClass.enclosingMethod.name
        //Log.i(TAG,"$fName r = " + r)
        return r
    }
    */

    /*
    // https://stackoverflow.com/questions/50121059/create-arraylist-as-parameter-of-a-function-that-accepts-any-type-in-kotlin
    // https://stackoverflow.com/a/50121229
    fun <T> randomizeArray(array: Array<T>?) : Array<T>? {
        array!!.shuffle()
        val saveArr = array.copyOf()
        val fName = object{}.javaClass.enclosingMethod.name
        Log.i(TAG,"\n######\n$fName: " + Arrays.toString(saveArr))
        return array
    }
    */

}