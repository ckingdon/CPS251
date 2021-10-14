package edu.ck.w07_addnamesavedata.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import edu.ck.w07_addnamesavedata.R


import edu.ck.w07_addnamesavedata.databinding.MainFragmentBinding // view binding

// view binding ...
private var _binding: MainFragmentBinding? = null // create null binding
private val binding get() = _binding!! // get the binding
// this creates a null placeholder for the binding then uses getter to get() it
// (though I don't entirely understand the syntax)

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //return inflater.inflate(R.layout.main_fragment, container, false)
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // makes sense
    }

    /*
    // onActivityCreated() is deprecated
    // replace with onViewCreated() .. below
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }
    */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Use the ViewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.tvDisplayNames.text = viewModel.getNameList().toString()

        binding.btAddName.setOnClickListener {
                //v: View -> buttonClicked(v)
            // need if here
            viewModel.addNameToList(binding.etEnterName.text.toString())

            // btAddName does nothing if user doesn't enter a name or enters just whitespace
            // could use isNotEmpty() but isNotBlank() also checks for whitespace-only
            if (binding.etEnterName.text.isNotBlank()) {
                binding.tvDisplayNames.text = viewModel.getNameList().toString()
            }

            // added per Scott's class demo
            // without this the value entered will persist after btAddName is clicked
            // this resets etEnterName so that its hint reappears
            binding.etEnterName.setText(null);
        }

        binding.btClearNames.setOnClickListener {
            binding.tvDisplayNames.text = viewModel.clearNameList()
            //binding.etEnterName.setText("enter name");
            binding.etEnterName.setText(null);
        }
    }
}
