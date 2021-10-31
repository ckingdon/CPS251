package edu.ck.w09_lifecycleaware.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.LifecycleRegistry
import edu.ck.w09_lifecycleaware.DemoObserver
import edu.ck.w09_lifecycleaware.databinding.MainFragmentBinding


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel // access INSTANCE properties
    lateinit var binding: MainFragmentBinding

    val lifecycleRegistry: LifecycleRegistry

    init {
        lifecycleRegistry = LifecycleRegistry(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = MainFragmentBinding.inflate (
            inflater, container, false )

        //binding.setLifecycleOwner(this) // not needed bc Fragments are lifecycleOwners by default

        return binding.root
    }

    // onDestroyView() no longer needed .. from the textbook ...
    // The binding object will only need to remain in memory for as long as the fragment is present. To ensure that the
    // instance is destroyed when the fragment goes away, the current fragment is declared as the lifecycle owner for
    // the binding object.


    override fun onViewCreated (view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get viewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // lifecycleRegistry initialized above for MainFragment class
        // lifecycle comes from built-in getLifecycle() .. so override getLifecycle() is not needed here
        lifecycle.addObserver(DemoObserver())

        // Logcat
        //val TAG = "MF"
        //Log.i(TAG, "msgStr is " + viewModel.getMsgText().value.toString())

        // create observer
        val msgObserver = Observer<String> {
                someText -> binding.tvEventOutput.text = someText // add toString() ???
        }

        // register observer to notice when state change happens
        viewModel.getMsgText().observe(viewLifecycleOwner, msgObserver)

        // send whatever getMsgText() returns to tvEventOutput
        binding.tvEventOutput.text = viewModel.getMsgText().value.toString()

    }
}