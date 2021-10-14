package edu.ck.w07_addnamesavedata1.ui.main

//import androidx.lifecycle.ViewModelProvider
import android.content.Context // added for onAttach()
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log // for Logcat
import androidx.lifecycle.ViewModelProvider

//import edu.ck.w07_addnamesavedata1.R // not needed bc now view binding is used

import edu.ck.w07_addnamesavedata1.databinding.MainFragmentBinding
//import java.lang.ClassCastException // this was added but may not be necessary ???


// add this for view binding:
private var _binding: MainFragmentBinding? = null
private val binding get() = _binding!!
// this creates a null placeholder for the binding then uses getter to get() it
// though I don't get the syntax

class MainFragment : Fragment() {

    // NEED this:
    private lateinit var viewModel: MainViewModel

    // https://medium.com/default-to-open/android-then-and-now-callbacks-b0fe9f756b20
    // check: is this callback only used for Log ???
    var activityCallback: MainFragment.MainFragmentListener? = null

    interface MainFragmentListener{
        fun onButtonClick(text: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            activityCallback = context as MainFragmentListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString() + " *MUST* implement MainFragmentListener interface"
                // try to run the app ... this message will go to log
                // if *MainActivity* doesn't implement the interface
                // results in runtime error FATAL EXCEPTION
            )
        }
    }

    // is this just a generic way to create an instance of the MainFragment ???
    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //return inflater.inflate(R.layout.main_fragment, container, false)
        // updated to use view binding
        _binding = MainFragmentBinding.inflate(inflater, container,false)
        return binding.root
    }

    // check: is this necessary for this app to work ???
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*
    // onActivityCreated() is deprecated
    // replace with onViewCreated() .. below
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }
    */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Use the ViewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.btAddName.setOnClickListener {
            v: View -> buttonClicked(v)
        }
    }

    // not sure what .text. is here .. or anywhere else where it is used like this
    // ok .. .text. comes from the MainFragmentListener interface's onButtonClick()
    private fun buttonClicked(view: View){
        Log.i("buttonClicked .. was name added????", binding.btAddName.text.toString())
        var content = binding.btAddName.text.toString()
        activityCallback?.onButtonClick(content)
    }

    fun addNameToList(text: String){
        binding.tvDisplayNames.text = text
    }

}