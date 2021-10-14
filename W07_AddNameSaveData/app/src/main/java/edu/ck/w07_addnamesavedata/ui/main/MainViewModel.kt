package edu.ck.w07_addnamesavedata.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private var name = ""
    //private var name = null // doesn't work
    private var nameList = ""

    fun addNameToList(name: String){
        //this.nameList += name
        this.nameList += "\n" + name
    }

    fun getNameList(): String {
        return nameList
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