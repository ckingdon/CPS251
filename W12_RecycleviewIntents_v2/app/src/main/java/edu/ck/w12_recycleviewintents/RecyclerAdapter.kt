package edu.ck.w12_recycleviewintents


import android.app.Activity
import android.content.Intent
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import java.io.File

// pass arrays as arguments: titles, details, images
class RecyclerAdapter (titles: Array<String>,
                       details: Array<String>,
                       images: Array<String>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    // declare the passed-in parameters here for local use
    private var titles = titles
    private var details = details
    private var images = images

    // implement ViewHolder class
    // this references the view elements (ImageView, TextView, TextView) in card_layout.xml
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init { // init works kinda like a constructor .. it sets things up
            // need to use findViewById bc view binding isn't / can't be used here for reasons
            itemImage = itemView.findViewById(R.id.itemImage)
            itemTitle = itemView.findViewById(R.id.itemTitle)
            itemDetail = itemView.findViewById(R.id.itemDetail)

            /*  Scott said ...
            Hi Everyone,

            I wanted to give you this hint for setting up your intent.  The following code creates
            the intent when a card is clicked.  The setOnClickListener is in the same spot as
            shown in the book on the recycleview tutorial chapter.

            itemView.setOnClickListener { v: View ->
                val i = Intent(v.getContext(),MainActivity2::class.java)
                //THERE WILL BE MORE CODE HERE

                startActivity(v.context,i,null)

            }*/

            // Responding to the card selection
            itemView.setOnClickListener { v: View  ->
                var position: Int = getAdapterPosition() // gets the position of the item that's clicked upon
                var title: String = titles[position]
                var detail: String = details[position]
                var image: String = images[position]

                // https://stackoverflow.com/questions/42468113/how-can-i-use-getresources-inside-of-onbindviewholder
                // https://stackoverflow.com/questions/9403321/android-how-to-retrieve-file-name-and-extension-of-a-resource-by-resource-id
                var imageProps = TypedValue()
                this.itemImage.resources.getValue(image, imageProps, true)
                var imageName = File(imageProps.string.toString()).nameWithoutExtension

                /*
                // use Snackbar to show details about the itemView that was clicked
                Snackbar.make(v, "You selected item at position $position. " +
                        "\n$title, $detail, ${imageName}",
                    Snackbar.LENGTH_LONG).setAction("Action", null).show()
                */

                // do Intent here .. instead of Snackbar
                val request_code = 42
                // val i = Intent(this, MainActivity2::class.java) // can't use "this" bc this is not an activity
                val i = Intent(v.context, MainActivity2::class.java)

                //val titleString = "hardcoded title"  // testing

                val titleString = title
                val detailsString = detail
                val imageString = image

                i.putExtra("tString", titleString)
                i.putExtra("dString", detailsString)
                i.putExtra("iString", imageString)

                //startActivity(i) // uses all the information attached to i to start the activity
                //startActivity(v.context, i,null)
                startActivityForResult(v.context as Activity, i, request_code, null)

                // might be better to somehow call back to MainActivity to do the INTENT there
                // could try to send title, detail, and image so that MainActivity could set up
                // the Intent and send along to MainActivity2

            }
        }

      
    }




    // RecyclerView (in content_main.xml) will call this to obtain a ViewHolder object
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_layout, viewGroup, false)
        // i: Int here is the layout id (int) that represents the view type from getItemViewType(int)
        // https://www.willowtreeapps.com/craft/android-fundamentals-working-with-the-recyclerview-adapter-and-viewholder-pattern
        return ViewHolder(v)
    }

    // populate the view hierarchy within the ViewHolder object with data to be displayed
    // recycling happens here: this builds the list that is used in each card
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        // i: Int here is the number of the ViewHolder that is being created

        // get item at position i of each array
        var title =  titles[i]
        var detail =  details[i]
        var image =  images[i]

        // set TextViews and ImageView card_layout.xml
        viewHolder.itemTitle.text = title
        viewHolder.itemDetail.text = detail
        viewHolder.itemImage.setImageResource(image.toInt()) // need toInt bc images array is <String>

    }

    // number of items to be used for number of recycled views
    override fun getItemCount(): Int {
        return titles.size // assume that title, details, images are arrays with same size
    }


}




