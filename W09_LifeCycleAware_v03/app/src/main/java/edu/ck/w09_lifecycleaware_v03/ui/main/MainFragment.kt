package edu.ck.w09_lifecycleaware_v03.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import androidx.lifecycle.Observer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import edu.ck.w09_lifecycleaware_v03.DemoObserver
import edu.ck.w09_lifecycleaware_v03.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    lateinit var binding: MainFragmentBinding

    //lateinit var lifecycleRegistry: LifecycleRegistry
    val lifecycleRegistry: LifecycleRegistry

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

        //lifecycleRegistry = LifecycleRegistry(this)

        // lifecycle comes from built-in getLifecycle() ...
        // so override getLifecycle() is not needed here
        // set DemoObserver to watch the lifecycle state of "this"
        lifecycle.addObserver(DemoObserver())


        //val TAG = "MF"
        //Log.i(TAG, "msgStr is " + viewModel.getMsgText().value.toString())

        //binding.tvEventOutput.text = viewModel.getMsgText().value.toString()

        val msgObserver = Observer<String> {

            someText -> binding.tvEventOutput.text = someText

        }

        viewModel.getMsgText().observe(viewLifecycleOwner, msgObserver)

        binding.tvEventOutput.setOnClickListener {

            binding.tvEventOutput.text = viewModel.getMsgText().value.toString()


        }


        // shouldn't need these bc fragments are lifecycle owners by default
        // START the owner
        //fun startOwner(){
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        //}
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