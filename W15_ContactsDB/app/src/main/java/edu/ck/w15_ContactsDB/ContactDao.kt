package edu.ck.w15_ContactsDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDao {

    // always running: class MainFragment .. fun observerSetup()
    @Query("SELECT * FROM contacts")
    fun getAllContacts(): LiveData<List<Contact>>

    // btAdd main_fragment.xml
    @Insert
    fun insertContact(contact: Contact)

    // btFind main_fragment.xml
    @Query("SELECT * FROM contacts WHERE Full_name LIKE '%' || :searchStr || '%'")
    fun findContacts(searchStr: String): List<Contact>

    // btReset main_fragment.xml
    @Query("SELECT * FROM contacts")
    fun resetContacts(): List<Contact>

    //btSortAsc main_fragment.xml
    @Query("SELECT * FROM contacts ORDER BY Full_name ASC")
    fun getSortedContactsAsc(): List<Contact>
    //fun getSortedContactsAsc(resultSet: MutableLiveData<List<Contact>>): List<Contact>

    //btSortDsc main_fragment.xml
    @Query("SELECT * FROM contacts ORDER BY Full_name DESC")
    fun getSortedContactsDsc(): List<Contact>

    // btTrashCan card_layout.xml to remove contact from database
    @Query("DELETE FROM contacts WHERE contactId = :id") // use id instead of name
    fun deleteContact(id: Int)

    // btDeleteAll main_fragment.xml to remove contact from database
    @Query("DELETE FROM contacts") // use id instead of name
    fun deleteAllContacts()


    /* from assignment instructions:
    @Insert
    fun insertContact(contact: Contact)

    //@Query("SELECT * FROM contacts WHERE contactName = :name")
    @Query("SELECT * FROM contacts WHERE Full_name LIKE '%' || :name || '%'")
    fun findContact(name: String): List<Contact>

    @Query("DELETE FROM contacts WHERE Full_name = :name")
    fun deleteContact(name: String)

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): LiveData<List<Contact>>
    */
}

