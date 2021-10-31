package edu.ck.w09_lifecycleaware

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import edu.ck.w09_lifecycleaware.ui.main.MainViewModel
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class DemoObserver: LifecycleObserver{

    // private val LOG_TAG = "DemoObserver"  // Logcat
    //private var viewModel: MainViewModel = MainViewModel() // access INSTANCE properties
    private var viewModel = MainViewModel // access STATIC (i.e. companion) properties

    // import java.time.format.DateTimeFormatter
    //https://stackoverflow.com/questions/49862357/how-do-i-get-the-current-time-as-a-timestamp-in-kotlin
    //https://www.baeldung.com/java-datetimeformatter
    private var curTime = ""
    private fun getTime(): String {
        curTime = DateTimeFormatter
            .ofPattern("HH:mm:ss.SSS")
            .format(LocalTime.now())
        return curTime
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){
        //Log.i(LOG_TAG, "onResume")
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        //Log.i(LOG_TAG, "$fName")
        viewModel.addMsg("$fName fired at " + getTime() + "\n*****")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        //Log.i(LOG_TAG, "onPause")
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        //Log.i(LOG_TAG, "$fName")
        viewModel.addMsg("$fName fired at " + getTime() + "\n*****")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        //Log.i(LOG_TAG, "onCreate")
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        //Log.i(LOG_TAG, "$fName")
        viewModel.addMsg("$fName fired at " + getTime())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(){
        //Log.i(LOG_TAG, "onStart")
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        //Log.i(LOG_TAG, "$fName")
        viewModel.addMsg("$fName fired at " + getTime())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(){
        //Log.i(LOG_TAG, "onStop")
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        //Log.i(LOG_TAG, "$fName")
        viewModel.addMsg("$fName fired at " + getTime())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        //Log.i(LOG_TAG, "onDestroy")
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        //Log.i(LOG_TAG, "$fName")
        viewModel.addMsg("$fName fired at " + getTime() + "\n*****")
    }

/*
    // not needed but leaving here for reference
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny(owner: LifecycleOwner, event: Lifecycle.Event){
        val fName = object{}.javaClass.enclosingMethod?.name // get name of current function
        Log.i(LOG_TAG, owner.lifecycle.currentState.name)
    }
*/

}