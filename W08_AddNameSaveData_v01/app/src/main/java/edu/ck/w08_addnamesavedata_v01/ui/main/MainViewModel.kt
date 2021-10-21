package edu.ck.w08_addnamesavedata_v01.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import android.util.Log

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val TAG = "MainViewModel: "

    private var name = ""
    //private var nameList = ""
    //private var nameList = ""
    private var nameList: MutableLiveData<String> = MutableLiveData()
    //private var nameList: MutableLiveData<ArrayList<String>> = MutableLiveData()

    fun addNameToList(name: String){
        if (nameList.value == null) {
            if (name.isNotBlank()) {
                this.nameList.setValue(name)
            }
        } else if (name.isNotBlank()) {
           this.nameList.setValue(nameList.value + "\n" + name.trim(' ')) // need concatenation
        }

        //for (name in nameListMutableLiveData) {
        //    nameList += name + "\n"
        //}


        // logging
        val fName = object{}.javaClass.enclosingMethod?.name // get name of the function I'm in now
        Log.i(TAG,"$fName adding $name to nameList") // filter on MainViewModel in Logcat

    }

    // DO I NEED THIS FUNCTION??? yes, it is needed
    fun getNameList(): MutableLiveData<String> {
        // logging
        val fName = object{}.javaClass.enclosingMethod?.name
        Log.i(TAG,"$fName returning " + this.nameList)

        return nameList

    }


    fun clearNameList() {
        // logging
        val fName = object{}.javaClass.enclosingMethod?.name
        Log.i(TAG,"$fName clearning nameList")

        this.nameList.setValue("")

        //return nameList
    }

}