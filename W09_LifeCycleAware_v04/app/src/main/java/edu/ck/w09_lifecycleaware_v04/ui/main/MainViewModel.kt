package edu.ck.w09_lifecycleaware_v04.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel : ViewModel() {

    companion object { // make static properties that both DemoObserver and MainFragment can access

        private val TAG = "MVMstatic"

        // functions here to let DemoObserver add to messageList ???
        // static = no need to make instance before accessing

        var msgStrC = ""
        //var msgStrC: MutableLiveData<String> = MutableLiveData()

        fun addMsg(msg: String){
            msgStrC += "\n" + msg
            //this.addMsg(msgStrC)
            val fName = object{}.javaClass.enclosingMethod?.name // get name of the function I'm in now
            Log.i(TAG, "$fName messageList is " + msgStrC)
        }

        fun getMsgTextC(): String {
            //msgStrC = MainViewModel.getMsgText()
            return msgStrC
        }
    }


    private val TAG = "MVMinstance"

    var msgStr: MutableLiveData<String> = MutableLiveData()

    // test with dummy messageList
    //init {        msgStr.value = "bortshoe"    }

    fun getMsgText(): MutableLiveData<String>{

        val fName = object{}.javaClass.enclosingMethod.name
        Log.i(TAG,"$fName returning " + Companion.msgStrC)

        msgStr.value = Companion.msgStrC
        //msgStr = this.msgStr
        return msgStr
    }


/*
    fun addMsg(msg: String){

        // logging
        val fName = object{}.javaClass.enclosingMethod?.name // get name of the function I'm in now
        //Log.i(TAG,"$fName adding " + message.value + " to messageList") // filter on MainViewModel in Logcat
        //Log.i(TAG,"$fName adding " + msg + " to messageList") // filter on MainViewModel in Logcat
        Log.i(TAG, "$fName messageList is " + msgStr.value)

        msgStr.value += "\n" + msg
        //msgStr.setValue(msgStr.value)

    }
*/



/*
    fun getMsgText(): MutableLiveData<String> {
    //fun returnMsgList(): String {

        val fName = object{}.javaClass.enclosingMethod.name
        Log.i(TAG,"$fName returning " + msgStr.value.toString())

        return msgStr
    }
*/

}