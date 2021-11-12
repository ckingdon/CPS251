package edu.ck.w11_recycleview


import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import java.io.File

// why not use lifecycle approach ...
// https://stackoverflow.com/questions/61364874/view-models-for-recyclerview-items

// pass arrays as arguments: titles, details, images
class RecyclerAdapter (titles: Array<String>, details: Array<String>, images: Array<String>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

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

            // Responding to the card selection
            itemView.setOnClickListener { v: View  ->
                var position: Int = getAdapterPosition() // gets the position of the item that's clicked upon
                var chapter: String = titles[position]
                var detail: String = details[position]
                var image: String = images[position]

                // https://stackoverflow.com/questions/42468113/how-can-i-use-getresources-inside-of-onbindviewholder
                // https://stackoverflow.com/questions/9403321/android-how-to-retrieve-file-name-and-extension-of-a-resource-by-resource-id
                var imageProps = TypedValue()
                this.itemImage.resources.getValue(image, imageProps, true)
                var imageName = File(imageProps.string.toString()).nameWithoutExtension

                // then show details about the item that was clicked
                Snackbar.make(v, "You selected item at position $position. " +
                        "\n$chapter, $detail, ${imageName}",
                    Snackbar.LENGTH_LONG).setAction("Action", null).show()
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




