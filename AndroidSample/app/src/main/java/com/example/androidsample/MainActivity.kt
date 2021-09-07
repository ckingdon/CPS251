package com.example.androidsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun convertCurrency(view: View) {
        if (dollarText.text.isNotEmpty()) {
            val dollarValue = dollarText.text.toString().toFloat()
            val euroValue = dollarValue * 0.85f
            textView.text = euroValue.toString()
            //textView.text = "%.2f".format(euroValue.toString())
            //dollarText.text = euroValue.toString()
        } else {
            textView.text = getString(R.string.no_value_string)
            //dollarText.text = getString(R.string.no_value_string)

        }
    }

}