package edu.ck.w09_lifecycleaware_v02.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry

import edu.ck.w09_lifecycleaware_v02.R

import edu.ck.w09_lifecycleaware_v02.DemoObserver

import edu.ck.w09_lifecycleaware_v02.databinding.MainFragmentBinding

// view binding ...
//private var _binding: MainFragmentBinding? = null // create null binding
//private val binding get() = _binding!! // get the binding

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
        binding = DataBindingUtil.inflate(
            inflater, R.layout.main_fragment, container, false
        )

        //R.layout.main_fragment

        //binding.setLifecycleOwner(this)
        //binding.lifecycleOwner = this


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        lifecycleRegistry = LifecycleRegistry(this)
        lifecycle.addObserver(DemoObserver())

        // START the owner
        //fun startOwner(){
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        //}

        //viewModel.addMsgToList()

        // STOP the owner
        //fun stopOwner(){
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        //}


        //binding.tvEventOutput.text = viewModel.getMsgList()
        //binding.tvEventOutput.text = "BORTCAKE"




    }

}