package edu.ck.w09_lifecycleaware.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel : ViewModel() {

    // make static properties (i.e. companion object) that DemoObserver can access
    // static = no need instantiate class before accessing companion object properties
    companion object {

        //private val TAG = "MVMstatic" // Logcat
        private var msgStr: MutableLiveData<String> = MutableLiveData()
        var msgStrC = ""

        fun addMsg(msg: String){

            // Logcat
            //val fName = object{}.javaClass.enclosingMethod?.name // get name of the function I'm in now
            //Log.i(TAG, "$fName messageList is " + msgStrC)

            msgStrC += "\n" + msg
            msgStr.value = msgStrC
        }
    }

    // Logcat
    //private val TAG = "MVMinstance"

    // fetch static property and make it accessible as an instance property ...
    // so that MainFragment can see it
    fun getMsgText(): MutableLiveData<String>{

        // Logcat
        //val fName = object{}.javaClass.enclosingMethod.name
        //Log.i(TAG,"$fName returning " + Companion.msgStrC)

        return msgStr
    }
}