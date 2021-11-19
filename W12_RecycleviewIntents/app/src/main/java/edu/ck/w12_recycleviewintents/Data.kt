package edu.ck.w12_recycleviewintents

class Data {

    val titles = arrayOf("Chapter One",
        "Chapter Two", "Chapter Three", "Chapter Four",
        "Chapter Five", "Chapter Six", "Chapter Seven",
        "Chapter Eight")

    val details = arrayOf("Item one details", "Item two details",
        "Item three details", "Item four details",
        "Item five details", "Item six details",
        "Item seven details", "Item eight details")

    /*
    val images = intArrayOf(R.drawable.android_image_1,
        R.drawable.android_image_2, R.drawable.android_image_3,
        R.drawable.android_image_4, R.drawable.android_image_5,
        R.drawable.android_image_6, R.drawable.android_image_7,
        R.drawable.android_image_8)
        */

    // made this Array<String> so I can use ONE randomizeArray() function in MainViewModel
    val images = arrayOf(R.drawable.android_image_1.toString(),
        R.drawable.android_image_2.toString(), R.drawable.android_image_3.toString(),
        R.drawable.android_image_4.toString(), R.drawable.android_image_5.toString(),
        R.drawable.android_image_6.toString(), R.drawable.android_image_7.toString(),
        R.drawable.android_image_8.toString())

}