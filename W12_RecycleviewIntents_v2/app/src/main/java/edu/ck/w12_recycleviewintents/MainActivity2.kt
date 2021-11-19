package edu.ck.w12_recycleviewintents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import edu.ck.w12_recycleviewintents.databinding.ActivityMain2Binding // view binding

import android.view.View
import android.content.Intent // for sending/receiving intents

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding


    //private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main2)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras ?: return

        val titleString = extras.getString("tString")
        val detailsString = extras.getString("dString")
        val imageString = extras.getString("iString")

        //binding.tvTitle.text = "FISH" // testing
        binding.tvTitle.text = titleString
        binding.tvDetail.text = detailsString
        //binding.tvDetail.text = imageString
        binding.ivImage.setImageResource(imageString!!.toInt())


        binding.btBackToCards.setOnClickListener {

                val data = Intent()

                val returnString = binding.tvTitle.text
                data.putExtra("returnData", returnString)

                setResult(RESULT_OK, data)

                super.finish()

            }

        }

}