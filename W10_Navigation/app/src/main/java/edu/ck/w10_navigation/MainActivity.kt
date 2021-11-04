package edu.ck.w10_navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.net.Uri

// listener interface
class MainActivity : AppCompatActivity(),
                    SecondFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

    }

    // listener interface requires implementation of onFragmentInteraction
    // but it remains blank because it won't be used here
    // however, it is implemented in SecondFragment.kt
    override fun onFragmentInteraction(uri: Uri) {
    }

}