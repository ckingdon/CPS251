package edu.ck.w10_navigation.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.ck.w10_navigation.databinding.MainFragmentBinding
import androidx.navigation.Navigation
import android.util.Log
import android.util.TypedValue
import androidx.core.view.get

class MainFragment: Fragment() {

    private val TAG = "MainFragment" // Logcat

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null // this creates a null placeholder for the binding
    private val binding get() = _binding!!     // then uses getter to get() it

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ###################################
        //          getImgProperties()
        // local function to get image details
        // ###################################
        fun getImgProperties(imgId: Int): MainFragmentDirections.MainToSecond {
            val action: MainFragmentDirections.MainToSecond =
                MainFragmentDirections.mainToSecond(imgId)

            // https://stackoverflow.com/questions/9403321/android-how-to-retrieve-file-name-and-extension-of-a-resource-by-resource-id
            var imgProperties = TypedValue()
            resources.getValue(imgId, imgProperties, true).toString() // puts all object details into imgProperties

            // messy regex to strip unwanted stuff .. gotta be a better way
            val regexPrefix = "^(.*?)_".toRegex()  // everything up to first _
            val regexSuffix = "(\\..*)\$".toRegex() // everything after last .
            val regexUnderscore = "_".toRegex()
            val imgFileName: String =
                regexUnderscore.replace(
                    regexSuffix.replace(
                        regexPrefix.replace(
                            imgProperties.string.toString(),
                            ""
                        ),
                        ""
                    ),
                    " "
                ).uppercase()

            /*
            Log.i(TAG, "### IMAGE Reference Id: " + imgId)
            Log.i(TAG, "### IMAGE File Name: " + imgFileName)
            Log.i(TAG, "### IMAGE Properties String " + imgProperties.string.toString()) // entire image details as string
            */

            // set action's imageMessage property
            action.imageMessage = imgFileName                         // stripped-down message to get "IMAGE #"

            // send action's imageReference property
            action.imageReference = imgId

            //Navigation.findNavController(it).navigate(action) // do this in setOnClickListener loop below

            return action
        }

        // ### loop approach
        for (i in 1..3){
            var imgId = resources.getIdentifier("android_image_" + i, "drawable", context?.packageName)
            var btnId = resources.getIdentifier("bt0" + i, "id", context?.packageName)

            // searching for btnId ...
            Log.i(TAG, "---------------------------------")
            Log.i(TAG, "### btnId: " + btnId)
            Log.i(TAG, "### binding.main.toString(): " + binding.main.toString())
            //Log.i(TAG, "### binding.main.get(btnId): " + binding.main.get(btnId)) // crash: java.lang.IndexOutOfBoundsException: Index: 2131230821, Size: 6
            Log.i(TAG, "### binding.main.getViewById(btnId): " + binding.main.getViewById(btnId))


            // use getViewById(btnId) .. NOT binding.main.get(btnId)
            binding.main.getViewById(btnId).setOnClickListener {
                Navigation.findNavController(it).navigate( getImgProperties(imgId))
            }

        } // for loop

        /*
        // ### linear approach
        // https://stackoverflow.com/Questions/3476430/how-to-get-a-resource-id-with-a-known-resource-name
        //var img1id = binding.ivImage01.id // crashes .. returns a different id than getIdentifier() approach
        // duh .. that's because I'm getting the ImageView rather than the image referenced in the ImageView
        var img1id = resources.getIdentifier("android_image_1", "drawable", context?.packageName)
        Log.i(TAG, "### IMAGE img1id: " + img1id)
        binding.bt01.setOnClickListener {
            Navigation.findNavController(it).navigate(getImgProperties(img1id))
        }
        var img2id = resources.getIdentifier("android_image_2", "drawable", context?.packageName)
        binding.bt02.setOnClickListener {
            Navigation.findNavController(it).navigate(getImgProperties(img2id))
        }
        var img3id = resources.getIdentifier("android_image_3", "drawable", context?.packageName)
        binding.bt03.setOnClickListener {
            Navigation.findNavController(it).navigate(getImgProperties(img3id))
        }
        */

    } // onViewCreated()

} // MainFragment












