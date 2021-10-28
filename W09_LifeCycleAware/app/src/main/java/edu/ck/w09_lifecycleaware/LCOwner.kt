package edu.ck.w09_lifecycleaware

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

class LCOwner: LifecycleOwner {

    /*
    The class needs a LifecycleRegistry instance initialized with a reference to itself,
    and a getLifecycle() method configured to return the LifecycleRegistry instance.
    Declare a variable to store the LifecycleRegistry reference, a constructor to
    initialize the LifecycleRegistry instance and add the getLifecycle() method.
    */
    private val lifecycleRegistry: LifecycleRegistry

    init {
        lifecycleRegistry = LifecycleRegistry(this)
        lifecycle.addObserver(DemoObserver())
    }

    /*
   Next, the class will need to notify the registry of lifecycle state changes.
   This can be achieved either by marking the state with the markState() method
   of the LifecycleRegistry object, or by triggering lifecycle events using
   the handleLifecycleEvent() method. What constitutes a state change within a
   custom class will depend on the purpose of the class. For this example, we
   will add some methods that simply trigger lifecycle events when called:
   */
    fun startOwner(){
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }
    fun stopOwner(){
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }


    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

}