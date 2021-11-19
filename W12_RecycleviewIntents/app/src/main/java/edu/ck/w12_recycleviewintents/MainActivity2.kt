package edu.ck.w12_recycleviewintents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import edu.ck.w12_recycleviewintents.databinding.ActivityMain2Binding // view binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main2)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // elvis: if intent.extras is not null then return intent.extras, otherwise do "return"
        val extras = intent.extras ?: return

        val titleString = extras.getString("tString") // retrieve tString from intent.extras
        val detailsString = extras.getString("dString")
        val imageString = extras.getString("iString")

        binding.tvTitle.text = titleString  // set tvTitle to titleString
        binding.tvDetail.text = detailsString
        binding.ivImage.setImageResource(imageString!!.toInt())
    }

}