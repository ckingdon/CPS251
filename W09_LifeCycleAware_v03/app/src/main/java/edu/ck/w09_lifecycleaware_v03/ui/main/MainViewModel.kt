package edu.ck.w09_lifecycleaware_v03.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel : ViewModel() {

    companion object { // make static properties that both DemoObserver and MainFragment can access

        private val TAG = "MVMstatic"

        // static = no need to make instance
        var msgStrC = ""
        // function here to let DemoObserver add to messageList ???

        fun addMsg(msg: String){
            msgStrC += "\n" + msg
            //this.addMsg(msgStrC)
            val fName = object{}.javaClass.enclosingMethod?.name // get name of the function I'm in now
            Log.i(TAG, "$fName messageList is " + msgStrC)
        }
    }

    //var getMsg get() = Companion.msgStrC // getter for msgStr

    /*fun getMsg () :String {
        return Companion.msgStrC
    }
    */

    private val TAG = "MVMinstance"

    var msgStr: MutableLiveData<String> = MutableLiveData()

    // test with dummy messageList
    //init {        msgStr.value = "bortshoe"    }

    fun getMsgText(): MutableLiveData<String>{

        val fName = object{}.javaClass.enclosingMethod.name
        Log.i(TAG,"$fName returning " + Companion.msgStrC)

        msgStr.value = Companion.msgStrC
        //msgStr.setValue(Companion.msgStrC)
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