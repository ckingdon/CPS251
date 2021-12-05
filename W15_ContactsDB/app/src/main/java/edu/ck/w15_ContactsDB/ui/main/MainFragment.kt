package edu.ck.w15_ContactsDB.ui.main

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import edu.ck.w15_ContactsDB.databinding.MainFragmentBinding
import androidx.recyclerview.widget.LinearLayoutManager
import edu.ck.w15_ContactsDB.Contact

// INTERFACE for sending contactId to MainFragment
interface TrashCanClickListenerInterface {
    fun onTrashCanClick(contact: Contact)
}


class MainFragment : Fragment(), TrashCanClickListenerInterface { // implement INTERFACE

    private var adapter: ContactListAdapter? = null
    private var alertDialog: AlertDialog? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // calling these local functions to keep things organized and readable
        listenerSetup()
        observerSetup()

        recyclerSetup()
    }

    // used several times in listenerSetup()
    private fun clearFields() {
        //binding.tvContactId.text = ""
        binding.etFullName.setText("")
        binding.phoneNumber.setText("")
    }

    // https://stackoverflow.com/questions/22505336/email-and-phone-number-validation-in-android/22505377
    private fun isValidPhoneNumber(pNumber: String) : Boolean {
        return android.util.Patterns.PHONE.matcher(pNumber).matches()
    }

    // 75.11 Adding the Button Listeners
    private fun listenerSetup(){

        // add record to db
        binding.btAdd.setOnClickListener {
            val name = binding.etFullName.text.toString()
            //val phone = binding.phoneNumber.text.toString()
            val phone = binding.phoneNumber.text.toString()
            //PhoneNumberFormattingTextWatcher(phone)

            if (name != "" && phone != "" && isValidPhoneNumber(phone)) {
                val contact = Contact(name, phone)
                // ### DB query function ####
                viewModel.insertContact(contact)
                clearFields()
                // Toast msg: confirm Contact has been added
                Toast.makeText(context, contact.fullName + " has been added to contacts database.", Toast.LENGTH_SHORT).show()
            } else {
                // Toast msg: both name and phone number are required before adding record
                Toast.makeText(context, "Please provide both a name and proper phone number.", Toast.LENGTH_SHORT).show()
            }
        }

        // find contact by name
        // eventually links back to ContactDao()'s findContacts()
        binding.btFind.setOnClickListener {
            val name = binding.etFullName.text.toString()
            if (name.isEmpty()) {
                // Toast here to tell user that Contact has been added to the db
                Toast.makeText(context, "Please provide a string to search on.", Toast.LENGTH_SHORT).show()
            } else {
                // ### DB query function ####
                viewModel.findContacts(binding.etFullName.text.toString())
                clearFields()
            }
        }

        // sorting: Ascending
        binding.btSortAsc.setOnClickListener {
            // ### DB query function ####
            viewModel.getSortedContactsAsc()
        }

        // sorting: Descending
        binding.btSortDsc.setOnClickListener {
            // ### DB query function ####
            viewModel.getSortedContactsDsc()
        }

        // reset list of contacts .. return list to original DB order based on primary key
        binding.btReset.setOnClickListener {
            clearFields() // duh
            // ### DB query function ####
            viewModel.findContacts(binding.etFullName.text.toString())
        }

        // add a set of pre-defined contacts
        binding.btPopulate.setOnClickListener {
            clearFields()
            viewModel.populateDB()
        }

        // clear the database
        binding.btDeleteAll.setOnClickListener {
            clearFields()
            viewModel.deleteAllContacts()
        }

    }

    // 75.12 Adding LiveData Observers
    private fun observerSetup(){
        // The “all contacts” observer simply passes the current list of contacts to
        // the setContactList() method of the RecyclerAdapter where the displayed list will be updated.
        viewModel.getAllContacts()?.observe(this, { contacts ->
            contacts?.let {
                adapter?.setContactList(it)
            }
        })

        // The “search results” observer checks that at least one matching result has been
        // located in the database, extracts the first matching Contact entity object from
        // the list, gets the data from the object, converts it where necessary and assigns
        // it to the TextView and EditText views in the layout.

        // need to use contactId here to make a new list i.e. setContactList
        // so you'll need a list of all the matching customerId's .. not just the first match
        viewModel.getSearchResults().observe(this, { contacts ->
            contacts?.let{
                if (it.isNotEmpty()) {
                    val idList: MutableList<Int> = mutableListOf()
                    for (customer in it){
                        idList.add(customer.id)
                    }

                    adapter?.setContactList(it)

                } else {
                    //binding.tvContactId.text = "NO MATCH" // handy for testing
                    val searchStr = binding.etFullName.text.toString()
                    Toast.makeText(context, "No contacts match your search: " + searchStr,  Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    // 75.13 Initializing the RecyclerView
    private fun recyclerSetup(){
        //adapter = ContactListAdapter()
        adapter = ContactListAdapter(this) // sending "this" as the trashCanListener
        binding.contactRecycler.layoutManager = LinearLayoutManager(context)
        binding.contactRecycler.adapter = adapter
    }

    // implement the method required by interface TrashCanClickListenerInterface
    override fun onTrashCanClick(contact: Contact) {
        createDialog(contact) // create the dialog when trashcan is clicked
        alertDialog?.show()
        // moved Toast and deleteContact() to createDialog()
    }

    // AlertDialog to confirm delete
    // https://blog.waldo.io/kotlin-alertdialog/
    private fun createDialog(contact: Contact) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        //alertDialogBuilder.setTitle("Remove contact from database?")
        //alertDialogBuilder.setMessage("Are you sure ${contact.fullName} from database?")

        alertDialogBuilder.setTitle("Are you sure you want to remove ${contact.fullName} from database?")

        alertDialogBuilder.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
            Toast.makeText(context, "You have deleted  " + contact.fullName,  Toast.LENGTH_SHORT).show()
            // ### DB query function ####
            viewModel.deleteContact(contact.id)
        }
        alertDialogBuilder.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
            // nothing needed here .. do nothing if user clicks 'No'
        }

        alertDialog = alertDialogBuilder.create()
    }

}

