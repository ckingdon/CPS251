package edu.ck.w09_lifecycleaware.ui.main

import android.accounts.AccountManager.get
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import edu.ck.w09_lifecycleaware.BR
import edu.ck.w09_lifecycleaware.R
//import edu.ck.w09_lifecycleaware.BR.messageView
import edu.ck.w09_lifecycleaware.databinding.MainFragmentBinding
import edu.ck.w09_lifecycleaware.DemoObserver

import edu.ck.w09_lifecycleaware.BR.messageView // id 'kotlin-kapt' in build.gradle !!!


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    lateinit var binding: MainFragmentBinding

    // lifecycle stuff
    lateinit var lifecycleRegistry: LifecycleRegistry




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        //return inflater.inflate(R.layout.main_fragment, container, false)

        // if using databinding, need these two lines ...
        /*
        binding = DataBindingUtil.inflate(
            inflater, R.layout.main_fragment, container, false
        )
        */
        binding = DataBindingUtil.inflate(
            inflater, R.layout.main_fragment, container, false
        )

        //binding.setLifecycleOwner(this)
        binding.lifecycleOwner = this


        return binding.root
    }


    /*
    // onActivityCreated() is deprecated
    // replace with onViewCreated() .. below
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }
    */

    //lateinit var lcOwner: LCOwner // if using DemoOwner class (below)

    override fun onViewCreated (view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // for databinding approach
        binding.setVariable(messageView, viewModel)


        lifecycleRegistry = LifecycleRegistry(this)
        // lifecycle comes from build-in getLifecycle()
        lifecycle.addObserver(DemoObserver()) // now DemoObserver is watching the lifecycle



        // START the owner
        //fun startOwner(){
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        //}

        //viewModel.addMsgToList()

        // STOP the owner
        //fun stopOwner(){
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        //}



        // shouldn't need this either
        /*
        lcOwner = LCOwner()
        lcOwner.startOwner()
        lcOwner.stopOwner()
        */


    }
}

/*
// check if this is needed
// shouldn't need it bc Fragments are, by default, lifecycle owners
// but it's move intuitive this way .. i.e. to farm it out to another class
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
        lifecycle.addObserver(DemoObserver()) // now DemoObserver is watching
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
*/