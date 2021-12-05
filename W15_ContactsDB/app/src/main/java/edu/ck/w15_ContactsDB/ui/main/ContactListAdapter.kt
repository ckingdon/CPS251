package edu.ck.w15_ContactsDB.ui.main



import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.ck.w15_ContactsDB.Contact
import edu.ck.w15_ContactsDB.R


// 75.9 Adding the RecyclerView Adapter

class ContactListAdapter (trashCanListener: TrashCanClickListenerInterface):
                        RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    /* works here, but doesn't make sense to me to put it here
    // INTERFACE for sending contactId to MainFragment
    interface TrashCanClickListenerInterface {
        fun onTrashCanClick(contactId: Int?)
    }
    */

    /*
    - require this INTERFACE when constructing ContactListAdapter() [see above]
    - also need to pass this INTERFACE when creating the ViewHolder
    This is VERY circular and I need to better understand how it is wired.
    The interface is in MainFragment.kt, but not inside class MainFragement().
    MainFragment() implements interface and then then passes it here, to ContactListAdapter().
    Track trashCanListener through this file to understand how it ends up in ViewHolder
    */

    private var contactList: List<Contact>? = null
    private var trashCanListener = trashCanListener

    // populate the view hierarchy within the ViewHolder object with data to be displayed
    // recycling happens here: this builds the list that is used in each card
    override fun onBindViewHolder(holder: ContactListAdapter.ViewHolder, listPosition: Int) {
        // "holder" comes from inner class ViewHolder()
        val contactName = holder.contactName
        val contactPhone = holder.contactPhone
        val contactId = holder.contactId

        contactList.let {
            contactName.text = it!![listPosition].fullName
            contactPhone.text = it!![listPosition].phoneNumber
            contactId.text = it!![listPosition].id.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                                .inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view, trashCanListener)
    }

    fun setContactList(contacts: List<Contact>) {
        contactList = contacts
        notifyDataSetChanged() // built-in to notify registered observers that the data set has changed.
    }

    override fun getItemCount(): Int {
        return if (contactList == null) 0 else contactList!!.size
    }

    // implement ViewHolder class using items from in card_layout.xml
    // inner class can access members of outer class .. so need this to be inner to access contactList
    inner class ViewHolder(itemView: View, trashCanListener: TrashCanClickListenerInterface) : RecyclerView.ViewHolder(itemView) {

        // these are all sent "up" to onBindViewHolder()
        var contactName: TextView = itemView.findViewById(R.id.tvContactName)
        var contactPhone: TextView = itemView.findViewById(R.id.tvContactPhone)
        var contactId: TextView = itemView.findViewById(R.id.tvHiddenContactId)
        var btTrashCan: ImageButton = itemView.findViewById(R.id.btTrashCan)

        init {
            // need to listen for clicks on btTrashCan
            btTrashCan.setOnClickListener {

                /*
                btTrashCan button will be created inside each card as it is generated
                by onBindViewHolder, so clicking on the button can give the clicked
                item's position in the list. The list position is used to access the
                Contact, then Contact's id, in the respective card. The Contact's id
                is sent to the onTrashCanClick() function (which comes from MainFragment).
                See other notes throughout for details on the interface that allows
                the Customer's id to be passed to MainFragment.
                */

                var trashCanListener = trashCanListener // listener is passed to ViewHolder() as parameter

                //val position: Int = getAdapterPosition() // get list position of the item that's been clicked
                val position: Int = adapterPosition // get list position of the item that's been clicked

                val contact: Contact? = contactList?.get(position) // get the entire Contact object

                Log.i(
                    "## XXX ContactListAdapter ViewHolder init ##",
                    contact?.id.toString() + " " + contact?.fullName
                )

                // use the interface to get Contact back to MainFragment
                trashCanListener.onTrashCanClick(contact!!)

            }

            /*
            // listen here only so user gets notification that they need to click trash can
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                val contact: Contact? = contactList?.get(position)
                //Toast.makeText(v, "You have deleted  " ,  Toast.LENGTH_SHORT).show()
                //Snackbar.make(v, "You clicked on ${contact?.fullName}", Snackbar.LENGTH_LONG)
                Snackbar.make(v, "You clicked on ${position}", Snackbar.LENGTH_LONG)
            }
            */

        }
    }
}







