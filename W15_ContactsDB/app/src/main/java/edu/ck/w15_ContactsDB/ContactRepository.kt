package edu.ck.w15_ContactsDB


import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

/*
The repository class will be responsible for interacting with the Room database on behalf of the ViewModel and
will need to provide methods that use the DAO to insert, delete and query product records. With the exception
of the getAllProducts() DAO method (which returns a LiveData object) these database operations will need to
be performed on separate threads from the main thread.
*/

class ContactRepository(application: Application) {

    val searchResults = MutableLiveData<List<Contact>>() // for RecycleView
    private var contactDao: ContactDao?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allContacts: LiveData<List<Contact>>? // for RecycleView
    //private var singleContact : List<Contact>? = listOf<Contact>((0,'fish','999'))
    private var singleContact : List<Contact>? = emptyList<Contact>()


    init {
        val db: ContactRoomDatabase? = ContactRoomDatabase.getDatabase(application)
        contactDao = db?.contactDao()
        allContacts = contactDao?.getAllContacts()
    }

    // insert into db
    fun insertContact(newcontact: Contact) {
        coroutineScope.launch(Dispatchers.IO){
            asyncInsert(newcontact)
        }
    }
    private suspend fun asyncInsert(contact: Contact){
        contactDao?.insertContact(contact)
    }

    // find in db
    fun findContacts(searchStr: String) {
        coroutineScope.launch(Dispatchers.Main){
            searchResults.value = asyncFind(searchStr).await()
            for (f in searchResults.value!!) {
                Log.i("##findContacts## ", f.id.toString() + " " + f.fullName.toString())
            }
        }
    }
    private suspend fun asyncFind(searchStr: String): Deferred<List<Contact>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async contactDao?.findContacts(searchStr)
        }


    // return sorted ASC results .. resets list to allContacts (i.e. doesn't keep results of last find)
    fun getSortedContactsAsc() {
        coroutineScope.launch(Dispatchers.Main){
            searchResults.value = asyncGetSortedContactsAsc().await()
            // how to make it work with current searchResults, whether it's allContacts or a subset
            for (f in searchResults.value!!) {
                Log.i("##getSortedContactsAsc## ", f.id.toString() + " " + f.fullName.toString())
            }

        }
    }
    private suspend fun asyncGetSortedContactsAsc(): Deferred<List<Contact>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async contactDao?.getSortedContactsAsc()
        }

    // return sorted DESC results
    fun getSortedContactsDsc() {
        coroutineScope.launch(Dispatchers.Main){
            searchResults.value = asyncGetSortedContactsDsc().await()
        }
    }
    private suspend fun asyncGetSortedContactsDsc(): Deferred<List<Contact>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async contactDao?.getSortedContactsDsc()
        }

    // delete specific record from db
    fun deleteContact(id: Int) {
        coroutineScope.launch(Dispatchers.IO){
            asyncDelete(id)
        }
    }
    private suspend fun asyncDelete(id: Int){
        contactDao?.deleteContact(id)
    }


    // delete all records from db
    fun deleteAllContacts() {
        coroutineScope.launch(Dispatchers.IO){
            asyncDeleteAllContacts()
        }
    }
    private suspend fun asyncDeleteAllContacts(){
        contactDao?.deleteAllContacts()
    }

}




/* attempting to sort subsetted list (i.e. after findContacts)
    // return sorted ASC results
    fun getSortedContactsAsc(resultSet: MutableLiveData<List<Contact>>) {
        coroutineScope.launch(Dispatchers.Main){
            searchResults.value = asyncGetSortedContactsAsc(resultSet).await()
            // how to make it work with current searchResults, whether it's allContacts or a subset
            */
/*for (f in searchResults.value!!) {
                Log.i("##getSortedContactsAsc## ", f.id.toString() + " " + f.fullName.toString())
            }*//*

        }
    }
    private suspend fun asyncGetSortedContactsAsc(resultSet: MutableLiveData<List<Contact>>): Deferred<List<Contact>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async contactDao?.getSortedContactsAsc(resultSet)
        }
*/





