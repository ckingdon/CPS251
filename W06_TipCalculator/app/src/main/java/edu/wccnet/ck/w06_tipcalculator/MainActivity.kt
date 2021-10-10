package edu.wccnet.ck.w06_tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import edu.wccnet.ck.w06_tipcalculator.databinding.ActivityMainBinding
import kotlin.text.StringBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // calculateTip()
    // based on Ch 18 currency convertor example
    // at startup, etNumberHint is displayed
    // if btCalc tip is clicked .. onClick connects to calculateTip()
    // calculateTip()
    //      if etNumber is empty then display error message
    //      if etNumber is entered then do calculation

    fun calculateTip(view: View){

        if (binding.etNumber.text.isEmpty()){
            // getting warning here, but I don't know how to use strings.xml
            // to show this *after* click happens
            binding.tvTipAmt.text = "YOU MUST ENTER A BILL AMOUNT"
        } else {
            val billAmt = binding.etNumber.text.toString().toFloat()
            val tipMsg = StringBuilder("The bill + tip amounts are: \n\n")
            // use loop to concatenate to tipMsg string
            for (i in 10 until 21 step 5 ) {
                val tipAmt = ( "%.2f".format( (billAmt * (1 + (i / 100f)) ) ) )
                tipMsg.append("\t$i% = \$$tipAmt \n")
            }

            binding.tvTipAmt.text = tipMsg
        }

    }
}