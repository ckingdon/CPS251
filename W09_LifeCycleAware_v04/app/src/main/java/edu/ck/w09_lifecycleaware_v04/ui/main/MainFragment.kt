package edu.ck.w09_lifecycleaware_v04.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil


import androidx.lifecycle.Observer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import edu.ck.w09_lifecycleaware_v04.DemoObserver
import edu.ck.w09_lifecycleaware_v04.R
import edu.ck.w09_lifecycleaware_v04.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    lateinit var binding: MainFragmentBinding
    private var vmStatic = MainViewModel

    //lateinit var lifecycleRegistry: LifecycleRegistry
    val lifecycleRegistry: LifecycleRegistry

    //var oldState = ""
    //var newState = ""

    init {
        lifecycleRegistry = LifecycleRegistry(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = MainFragmentBinding.inflate (
            inflater, container, false )

        return binding.root
    }

    override fun onViewCreated (view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get viewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        // to access viewModel's static properties
        //vmStatic.getMsgTextC()

        //lifecycleRegistry = LifecycleRegistry(this)

        // lifecycle comes from built-in getLifecycle() ...
        // so override getLifecycle() is not needed here
        // set DemoObserver to watch the lifecycle state of "this"
        lifecycle.addObserver(DemoObserver())

        //val TAG = "MF"
        //Log.i(TAG, "msgStr is " + viewModel.getMsgText().value.toString())

        // create observer
        val msgObserver = Observer<String> {
                someText -> binding.tvEventOutput.text = someText
        }
        // register observer to notice when state change happens
        viewModel.getMsgText().observe(viewLifecycleOwner, msgObserver)


        binding.tvEventOutput.setOnClickListener {
            binding.tvEventOutput.text = viewModel.getMsgText().value.toString()
        }






/*

        oldState = viewModel.getMsgText().value.toString()
        newState = oldState + viewModel.getMsgText().value.toString()
        binding.tvEventOutput.text = newState
*/




        // shouldn't need these ??? bc fragments are lifecycle owners by default
        // and lifecycle events are being triggered by app on/device rotation/app background/etc
        // START the owner
        //lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        // STOP the owner
        //lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)



        // shouldn't need these either
        /*
        lcOwner = LCOwner()
        lcOwner.startOwner()
        lcOwner.stopOwner()
        */


    }
}