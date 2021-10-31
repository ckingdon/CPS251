
package edu.ck.w08_addnamesavedata.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import android.util.Log

class MainViewModel : ViewModel() {

    private val TAG = "MainViewModel: "

    //private var name = ""
    //private var nameList = ""
    // REMOVE private to expose to MainFragmentBinding
    var name: MutableLiveData<String> = MutableLiveData()
    var nameList: MutableLiveData<String> = MutableLiveData()
    // stuck with using a string rather than an ArrayList
    //private var nameList: MutableLiveData<ArrayList<String>> = MutableLiveData()


    fun addNameToList(){
        // dummy values to test @= two-way binding in main_fragment.xml
        //name.setValue("name BORT")
        //nameList.setValue("nameList BORT")

        // using let approach from textbook
        name.let{
            if (!it.value.isNullOrBlank()){
                //nameList.value = it.value + "\n" + name.toString()
                if (nameList.value == null) {
                    nameList.value = it.value
                } else {
                    nameList.value += "\n" + it.value
                }

            }
            it.setValue(null) // reset name so that "enter name" hint appears after each name is added
        }

/*

        //old approach, adapted from last week's assignment
        if (nameList.value == null) {
            if (name.value?.isNotBlank() == true) {
                this.nameList.setValue(name.value)
            }
        } else if (name.value?.isNotBlank() == true) {
            this.nameList.setValue(nameList.value + "\n" + name.value!!.trim(' ')) // need concatenation
        }
        // nameList.value = name.value + "\n" + nameList.value
*/


        // logging
        val fName = object{}.javaClass.enclosingMethod?.name // get name of the function I'm in now
        Log.i(TAG,"$fName adding $name.value to nameList") // filter on MainViewModel in Logcat

    }

    fun clearNameList() { // clears out vars by setting them to null

        this.name.setValue(null)
        this.nameList.setValue(null)

        // logging
        val fName = object{}.javaClass.enclosingMethod?.name
        Log.i(TAG,"$fName clearing nameList")
    }

}