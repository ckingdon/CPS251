package edu.ck.w12_recycleviewintents

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider

import edu.ck.w12_recycleviewintents.databinding.ActivityMainBinding // view binding

//import android.view.View
//import android.content.Intent // for sending/receiving intents


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivityX "

    // "import" Data.kt
    private var data = Data()

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) { // called when device is rotated
        super.onCreate(savedInstanceState)

        // gain access to MainViewModel() class
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // initialize the RecyclerView with a layout manager
        // create an instance of the adapter and assign that instance to the RecyclerView object
        // configure the RecyclerView to use the LinearLayoutManager layout option

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // getting stuff from RecyclerView that's inside content_main.xml
        layoutManager = LinearLayoutManager(this)
        binding.contentMain.recyclerView.layoutManager = layoutManager

        val fName = object{}.javaClass.enclosingMethod?.name // for Log message below

        //adapter = RecyclerAdapter() // instance of RecycleAdapter() // no parameters passed
        //adapter = RecyclerAdapter(data.titles, data.details, data.images) // pass data as parameters

        // ###########################################################################################
        // if savedInstanceState is NULL (i.e. first run) then use viewModel to get randomized arrays
        // else (i.e. not first run and therefore arrays have already been randomized) then restore saved arrays
        // ###########################################################################################
        if (savedInstanceState == null){        // FRESH START: fetch randomized arrays
            Log.i(TAG,"###### $fName savedInstanceState is NULL")

            val tArr = viewModel.randomizeArray(data.titles)
            val dArr = viewModel.randomizeArray(data.details)
            val iArr = viewModel.randomizeArray(data.images)

            adapter = RecyclerAdapter(tArr, dArr, iArr) // use randomized arrays to create adapter

        } else {                                // RESTORE STATE: fetch previously-randomized arrays
            //Log.i(TAG,"\n######\n$fName: save2dArr" + viewModel.save2dArr[0].contentToString())
            viewModel.save2dArr.forEach {
                Log.i(TAG,"###### $fName save2dArr " + it.contentToString())
            }

            // use restored arrays to create adapter
            adapter = RecyclerAdapter(viewModel.save2dArr[0],viewModel.save2dArr[1],viewModel.save2dArr[2])

        }

        binding.contentMain.recyclerView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    /* nope .. do the INTENT in RecyclerAdapter
    // need functionality to create and start an INTENT when user clicks on a card
    // in card_layout.xml, relativeLayout has onClick method .. use it

    fun sendCardInfo(view: View) {

        val i = Intent(this, MainActivity2::class.java)

        val titleString = "hardcoded title"
        val detailsString = "hardcoded details"

        i.putExtra("tString", titleString)
        //i.putExtra("tString", t1)
        i.putExtra("dString", detailsString)

        startActivity(i) // uses all the information attached to i to start the activity
    }
    */

}