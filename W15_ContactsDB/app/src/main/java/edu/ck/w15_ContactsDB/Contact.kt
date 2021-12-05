package edu.ck.w15_ContactsDB


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
class Contact {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "contactId")
    var id: Int = 0

    @ColumnInfo(name = "Full_name")
    var fullName: String? = null

    @ColumnInfo(name = "Phone_number")
    var phoneNumber: String? = null
    
    //var quantity: Int = 0 // annotation not necessary bc SQL queries in this exercise don't access this db column

    constructor(){}

    constructor(id: Int, contactName: String, contactPhone: String) {
        this.fullName = contactName
        this.phoneNumber = contactPhone
    }
    constructor(contactName: String, contactPhone: String) {
        this.fullName = contactName
        this.phoneNumber = contactPhone
    }


}