package edu.ck.w09_lifecycleaware_v03

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.LifecycleOwner
import edu.ck.w09_lifecycleaware_v03.ui.main.MainViewModel
import java.time.Instant
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class DemoObserver: LifecycleObserver{

    private val LOG_TAG = "DemoObserver"
    //private var viewModel: MainViewModel = MainViewModel() // for setting instance properties
    private var viewModel = MainViewModel // for setting static companion properties
    //private var vM = MainViewModel // consider another var name to keep it clear



    //need to import java.time.format.DateTimeFormatter
    //https://stackoverflow.com/questions/49862357/how-do-i-get-the-current-time-as-a-timestamp-in-kotlin
    //https://www.baeldung.com/java-datetimeformatter
    companion object {
        private var curTime = ""
        private fun getTime(): String {
            curTime = DateTimeFormatter
                .ofPattern("HH:mm:ss.SSS")
                .format(LocalTime.now())
            return curTime
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){
        //Log.i(LOG_TAG, "onResume")
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        Log.i(LOG_TAG, "$fName")
        viewModel.addMsg("$fName fired at " + Companion.getTime())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        //Log.i(LOG_TAG, "onPause")
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        Log.i(LOG_TAG, "$fName")
        viewModel.addMsg("$fName fired at " + Companion.getTime())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        //Log.i(LOG_TAG, "onCreate")
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        Log.i(LOG_TAG, "$fName")
        viewModel.addMsg("$fName fired at " + Companion.getTime())
        /*viewModel.addMsg("$fName fired at "
            + DateTimeFormatter
            .ofPattern("mm:ss.SSS")
            .format(Instant.now())   )*/
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(){
        //Log.i(LOG_TAG, "onStart")
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        Log.i(LOG_TAG, "$fName")
        viewModel.addMsg("$fName fired at " + Companion.getTime())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(){
        //Log.i(LOG_TAG, "onStop")
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        Log.i(LOG_TAG, "$fName")
        viewModel.addMsg("$fName fired at " + Companion.getTime())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        //Log.i(LOG_TAG, "onDestroy")
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        Log.i(LOG_TAG, "$fName")
        viewModel.addMsg("$fName fired at " + Companion.getTime())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny(owner: LifecycleOwner, event: Lifecycle.Event){
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        Log.i(LOG_TAG, owner.lifecycle.currentState.name)
        //viewModel.addMsg("****")
        //message = "ON_ANY was fired"
        //val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        //viewModel.message.setValue("$fName was fired")

    }

}