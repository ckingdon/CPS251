package edu.ck.w08_addnamesavedata_v01.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.Observer

import edu.ck.w08_addnamesavedata_v01.databinding.MainFragmentBinding // view binding


class MainFragment : Fragment() {

    // view binding ...
    private var _binding: MainFragmentBinding? = null // create null binding
    private val binding get() = _binding!! // get the binding
    // this creates a null placeholder for the binding then uses getter to get() it
    // (though I don't entirely understand the syntax)


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

        //binding.tvDisplayNames.text = viewModel.getNameList().toString()

        val nameListObserver = Observer<String> {
            //val nameListObserver = Observer<ArrayList<String>> {
            nameList -> binding.tvDisplayNames.text = nameList.toString()

        // probably don't need toString()
            // implement concatenation here or in viewModel's addNameToList ???
            //for (name in nameListMutableLiveData) {

            //}

        }

        viewModel.getNameList().observe(viewLifecycleOwner, nameListObserver)

        binding.btAddName.setOnClickListener {
                //v: View -> buttonClicked(v)
            // need if here
            viewModel.addNameToList(binding.etEnterName.text.toString())


            /*
            // btAddName does nothing if user doesn't enter a name or enters just whitespace
            // could use isNotEmpty() but isNotBlank() also checks for whitespace-only
            if (binding.etEnterName.text.isNotBlank()) {
                binding.tvDisplayNames.text = viewModel.getNameList().toString()
            }
            */

            // added per Scott's class demo
            // without this the value entered will persist after btAddName is clicked
            // this resets etEnterName so that its hint reappears
            binding.etEnterName.setText(null)
        }


        // need to use LiveData here too
        binding.btClearNames.setOnClickListener {
            //binding.tvDisplayNames.text = viewModel.clearNameList()
            viewModel.clearNameList()

            //binding.etEnterName.setText("enter name");
            binding.etEnterName.setText(null)
        }
    }
}
