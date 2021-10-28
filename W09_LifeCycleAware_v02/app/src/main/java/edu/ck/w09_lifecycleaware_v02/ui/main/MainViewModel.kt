package edu.ck.w09_lifecycleaware_v02.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {


    private val TAG = "MainViewModel"

    //var msg = ""
    //var msg: MutableLiveData<String> = MutableLiveData()

    //var msgList = ""
    var msgList : MutableLiveData<String> = MutableLiveData()

    fun addMsgToList(msg: String){

        val fName = object{}.javaClass.enclosingMethod.name // get name of the function I'm in now
        Log.i(TAG,"$fName setting nameList = " + this.msgList) // filter on MainViewModel in Logcat

        //this.nameList += name
        msgList.value += "\n" + msg

    }

    //fun returnMsgList(): String {
    fun returnMsgList(): String {

        val fName = object{}.javaClass.enclosingMethod.name
        Log.i(TAG,"$fName returning " + this.msgList)

        return msgList.value.toString()
    }



}