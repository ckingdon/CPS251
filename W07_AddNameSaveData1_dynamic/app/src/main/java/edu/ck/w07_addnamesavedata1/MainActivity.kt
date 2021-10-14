package edu.ck.w07_addnamesavedata1

//import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import edu.ck.w07_addnamesavedata1.ui.main.MainFragment
import edu.ck.w07_addnamesavedata1.databinding.MainActivityBinding // view binding

//class MainActivity : AppCompatActivity() {
class MainActivity : FragmentActivity(), MainFragment.MainFragmentListener {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.main_activity)
        // view binding
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFrag() // load the (only) fragment .. no waiting for button click

        /*
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        */

        }

    override fun onButtonClick(text: String) {
        TODO("Not yet implemented")
        val mainFrag = supportFragmentManager.findFragmentById(R.id.mainFrag) as MainFragment
        mainFrag.addNameToList(text)
    }

    fun loadFrag(){
        supportFragmentManager.beginTransaction()
            .add(R.id.mainFrag, MainFragment.newInstance(),"mainFish")
            .commit()
    }

    }
