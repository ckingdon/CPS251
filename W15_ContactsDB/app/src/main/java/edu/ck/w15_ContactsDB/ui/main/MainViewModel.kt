package edu.ck.w15_ContactsDB.ui.main


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel

import edu.ck.w15_ContactsDB.Contact
import edu.ck.w15_ContactsDB.ContactRepository


class MainViewModel(application: Application) : AndroidViewModel(application) {

    // initializing here instead of in init{} block
    private val repository: ContactRepository = ContactRepository(application)
    private val allContacts: LiveData<List<Contact>>? = repository.allContacts
    private val searchResults: MutableLiveData<List<Contact>> = repository.searchResults

    fun insertContact(contact: Contact){
        repository.insertContact(contact)
    }

    fun findContacts(searchStr: String){
        repository.findContacts(searchStr)
    }

    fun deleteContact(id: Int){
        // Toast for testing
        //Toast.makeText(getApplication(), "deleteContact(${id}) ",  Toast.LENGTH_SHORT).show()
        repository.deleteContact(id)
    }

    fun getSortedContactsAsc() {
        repository.getSortedContactsAsc()
    }

    fun getSortedContactsDsc(){
        repository.getSortedContactsDsc()
    }

    fun getSearchResults(): MutableLiveData<List<Contact>>{
        return searchResults
    }

    fun getAllContacts(): LiveData<List<Contact>>?{
        return allContacts
    }

    fun populateDB() {
        insertContact(Contact("Doris Kingdon", "613.226.8742") )
        insertContact(Contact("Clayton Kingdon", "608.205.8742") )
        insertContact(Contact("Clayton Hucklebone", "555.555.1212") )
        insertContact(Contact("Tom Waits", "212.392.7704") )
        insertContact(Contact("Zarley Zalapsky", "877.123.4567") )
        insertContact(Contact("John Waters", "555.555.5555") )
    }

    fun deleteAllContacts() {
        repository.deleteAllContacts()
    }



}