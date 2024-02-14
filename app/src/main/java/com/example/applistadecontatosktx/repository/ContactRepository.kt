package com.example.applistadecontatosktx.repository

import android.content.Context
import com.example.applistadecontatosktx.config.Database
import com.example.applistadecontatosktx.model.Contact
import com.example.applistadecontatosktx.model.ContactWithPhoto
import com.example.applistadecontatosktx.model.Photo

class ContactRepository(val context: Context) {

    private val daoContact = Database.getInstance(context)?.daoContact()

    fun insert(contact: Contact?): Boolean {
        return (contact != null && daoContact?.insert(contact)!! > 0)
    }

    fun delete(contact: Contact): Boolean {
        return daoContact?.delete(contact)!! > 0
    }

    fun update(contact: Contact): Boolean {
        return (daoContact?.update(contact)!! > 0)
    }

    fun getById(id: Long): ContactWithPhoto {
        return daoContact?.getByID(id) ?: ContactWithPhoto(Contact(), Photo(null))
    }

    fun getAll(): List<ContactWithPhoto> {
        return daoContact?.getAll() ?: ArrayList()
    }

}