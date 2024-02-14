package com.example.applistadecontatosktx.view.main

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applistadecontatosktx.R
import com.example.applistadecontatosktx.model.ContactWithPhoto
import com.example.applistadecontatosktx.repository.ContactRepository
import com.example.applistadecontatosktx.repository.PhotoRepository
import com.example.applistadecontatosktx.view.adapter.AdapterContact

/**
 * this annotation is added to satisfy the context-based memory leak alert
 * esta anotação é adicionada para satisfazer o alerta de vazamento de memoria com base no contexto
 * **/
@SuppressLint("StaticFieldLeak")
class MainViewModel(application: Application, val context: Context) :
    AndroidViewModel(application) {

    private val contactRepository = ContactRepository(application)
    private val photoRepository = PhotoRepository(application)
    private var listContact = ArrayList<ContactWithPhoto>()

    private var adapter = MutableLiveData<AdapterContact>()
    private var layoutManager = MutableLiveData<LinearLayoutManager>()

    init {
        listContact = ArrayList(contactRepository.getAll())
    }

    fun getAdapter(): LiveData<AdapterContact> {
        adapter.value = AdapterContact(context, this, listContact)
        return adapter
    }

    fun getLayoutManager(): LiveData<LinearLayoutManager> {

        layoutManager.value = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return layoutManager
    }

    fun showOrHideListContacts(parentView: View) {

        val child = parentView.findViewById<TextView>(R.id.empty_list_message)

        if (listContact.isEmpty()) child.visibility = View.VISIBLE
        else child.visibility = View.GONE
    }

    fun deleteContact(contato: ContactWithPhoto) {

        val dialog = AlertDialog.Builder(context)
            .setTitle(context.resources.getString(R.string.title_dialog_delete_contact))
            .setMessage(context.resources.getString(R.string.message_dialog_delete_contact))
            .setPositiveButton(context.resources.getString(R.string.response_positive_dialog_delete_contact)) { _, _ ->


                contactRepository.delete(contato.contact)
                photoRepository.delete(contato.photo)

                listContact.remove(contato)
                adapter.value?.notifyDataSetChanged()

                Toast.makeText(
                    context,
                    context.resources.getString(R.string.message_delete_contact_success),
                    Toast.LENGTH_LONG
                )
                    .show()
            }
            .setNegativeButton(context.resources.getString(R.string.response_positive_dialog_delete_contact)) { dialog, _ -> dialog.dismiss() }
            .create()

        dialog.show()

    }

    fun refreshListAdapter() {
        listContact = contactRepository.getAll() as ArrayList<ContactWithPhoto>
        adapter.value?.notifyDataSetChanged()
    }

}