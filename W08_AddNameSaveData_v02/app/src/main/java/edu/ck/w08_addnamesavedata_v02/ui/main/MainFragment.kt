package edu.ck.w08_addnamesavedata_v02.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

//import androidx.lifecycle.Observer

import edu.ck.w08_addnamesavedata_v02.databinding.MainFragmentBinding // view binding

import androidx.databinding.DataBindingUtil

import edu.ck.w08_addnamesavedata_v02.R

import edu.ck.w08_addnamesavedata_v02.BR.someViewModel


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        //return inflater.inflate(R.layout.main_fragment, container, false)
        //_binding = MainFragmentBinding.inflate(inflater, container, false)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.main_fragment, container, false
        )
        binding.setLifecycleOwner(this)
        return binding.root
        //return binding.getRoot() // textbook says return binding.getRoot()
    }

    // onActivityCreated() is deprecated
    // replace with onViewCreated() .. below
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Use the ViewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.setVariable(someViewModel, viewModel)
    }

}
