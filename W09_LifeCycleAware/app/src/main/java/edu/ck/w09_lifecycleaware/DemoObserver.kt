package edu.ck.w09_lifecycleaware

import android.util.Log
import androidx.lifecycle.*
import edu.ck.w09_lifecycleaware.ui.main.MainViewModel



class DemoObserver: LifecycleObserver {

    private val LOG_TAG = "DemoObserver"
    private var viewModel: MainViewModel = MainViewModel()
    //private var viewModel = MainViewModel
    //private var vM = MainViewModel
    //private var viewModel = MainViewModel // for setting static companion properties

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){
        Log.i(LOG_TAG, "onResume")
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        //viewModel.m = "Scott"
        //vM.m = "Scott"
        //viewModel.m = "$fName was fired"
        //viewModel.message.setValue("$fName was fired")
        //viewModel.addMsgToList()
        viewModel.addMsgToList("$fName was fired (OBSERVER)")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        Log.i(LOG_TAG, "onPause")
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        //viewModel.message.setValue("$fName was fired")
        //viewModel.m = "$fName was fired"
        //viewModel.message.setValue("$fName was fired")
        //viewModel.addMsgToList()
        viewModel.addMsgToList("$fName was fired (OBSERVER)")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        Log.i(LOG_TAG, "onCreate")
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        //viewModel.message.setValue("$fName was fired")
        //viewModel.m = "$fName was fired"
        //viewModel.message.setValue("$fName was fired")
        //viewModel.addMsgToList()
        viewModel.addMsgToList("$fName was fired (OBSERVER)")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(){
        Log.i(LOG_TAG, "onStart")
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        //viewModel.message.setValue("$fName was fired")
        //viewModel.m = "$fName was fired"
        //viewModel.message.setValue("$fName was fired")
        //viewModel.addMsgToList()
        viewModel.addMsgToList("$fName was fired (OBSERVER)")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(){
        Log.i(LOG_TAG, "onStop")
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        //viewModel.message.setValue("$fName was fired")
        //viewModel.m = "$fName was fired"
        //viewModel.message.setValue("$fName was fired")
        //viewModel.addMsgToList()
        viewModel.addMsgToList("$fName was fired (OBSERVER)")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        Log.i(LOG_TAG, "onDestroy")
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        //viewModel.message.setValue("$fName was fired")
        //viewModel.m = "$fName was fired"
        //viewModel.message.setValue("$fName was fired")
        //viewModel.addMsgToList()
        viewModel.addMsgToList("$fName was fired (OBSERVER)")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny(owner: LifecycleOwner, event: Lifecycle.Event){
        Log.i(LOG_TAG, owner.lifecycle.currentState.name)
        //message = "ON_ANY was fired"
        //val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        //viewModel.message.setValue("$fName was fired")

    }


}