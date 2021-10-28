package edu.ck.w09_lifecycleaware.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.ck.w09_lifecycleaware.BR

class MainViewModel : ViewModel() {

    companion object { // make static properties that both DemoObserver and MainFragment
        // static = no need to make instance
        var m = ""
        // function here to let DemoObserver add to messageList ???
        /*
        fun addMessage(msg: String){
            this.m += msg
        }
        */
    }

    private val TAG = "MainViewModel"

    // REMOVE private to expose to MainFragmentBinding
    var message: MutableLiveData<String> = MutableLiveData()
    var messageList: MutableLiveData<String> = MutableLiveData()
    // stuck with using a string rather than an ArrayList
    //private var nameList: MutableLiveData<ArrayList<String>> = MutableLiveData()

    // test with dummy messageList
    init {
        messageList.value = "bortshoe"
    }

    fun addMsgToList(msg: String){

        this.messageList.value += "---" + msg

        // logging
        val fName = object{}.javaClass.enclosingMethod?.name // get name of the function I'm in now
        //Log.i(TAG,"$fName adding " + message.value + " to messageList") // filter on MainViewModel in Logcat
        Log.i(TAG,"$fName adding " + msg + " to messageList") // filter on MainViewModel in Logcat
        Log.i(TAG, "$fName messageList is " + messageList.value)
    }

}