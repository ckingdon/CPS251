package edu.ck.w07_addnamesavedata.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val TAG = "MainViewModel: "

    private var name = ""
    //private var name = null // doesn't work
    private var nameList = ""

    fun addNameToList(name: String){
        //this.nameList += name
        this.nameList += "\n" + name
        val fName = object{}.javaClass.enclosingMethod.name // get name of the function I'm in now
        Log.i(TAG,"$fName setting nameList = " + this.nameList) // filter on MainViewModel in Logcat

    }

    fun getNameList(): String {
        return nameList
        val fName = object{}.javaClass.enclosingMethod.name
        Log.i(TAG,"$fName returning " + this.nameList)
    }

    fun clearNameList(): String {
    //fun clearNameList(): Void {
        this.nameList = ""
        return nameList
    }
/*

    fun resetName(): String {
        //this.name = "enter Name"
        return "bort"
    }
*/

}