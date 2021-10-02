package com.example.androidsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View

//import kotlinx.android.synthetic.main.activity_main.*
import com.example.androidsample.databinding.ActivityMainBinding // added CK 2021-09-17



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun convertCurrency(view: View) {
        //if (dollarText.text.isNotEmpty()) {
        if (binding.dollarText.text.isNotEmpty()) {
            //val dollarValue = dollarText.text.toString().toFloat()
            val dollarValue = binding.dollarText.text.toString().toFloat()
            //val scott = dollarText.text.toString()
            val euroValue = dollarValue * 0.85f
            //textView.text = euroValue.toString()
            binding.textView.text = euroValue.toString()
            //textView.text = "%.2f".format(euroValue.toString())
            //dollarText.text = euroValue.toString()
        } else {
            //textView.text = getString(R.string.no_value_string)
            binding.textView.text = getString(R.string.no_value_string)
            //dollarText.text = getString(R.string.no_value_string)
        }
    }
}