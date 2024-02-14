package com.example.applistadecontatosktx.view.cadastro

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.applistadecontatosktx.R
import com.example.applistadecontatosktx.commons.Utils
import com.example.applistadecontatosktx.model.Contact
import com.example.applistadecontatosktx.model.ContactWithPhoto
import com.example.applistadecontatosktx.model.Photo
import com.example.applistadecontatosktx.repository.ContactRepository
import com.example.applistadecontatosktx.repository.PhotoRepository


class RegisterContactViewModel(application: Application, val id: Long) :
    AndroidViewModel(application) {

    private val context = application
    private val contactRepo: ContactRepository = ContactRepository(context)
    private val photoRepo: PhotoRepository = PhotoRepository(context)

    private var objInternal: ContactWithPhoto = contactRepo.getById(id)
    private var localTempObj: ContactWithPhoto = ContactWithPhoto(Contact(), Photo(null))

    private var objInternalLiveData = MutableLiveData<ContactWithPhoto>()
    var icon = MutableLiveData<Bitmap>()
    private var firstname = MutableLiveData<String>()
    private var phoneticName = MutableLiveData<String>()
    private var lastname = MutableLiveData<String>()
    private var company = MutableLiveData<String>()
    private var phone = MutableLiveData<String>()

    init {
        if (objInternal.contact.id != null) fillEdits()

        // cria uma copia com os valores pre definidos antes de uma possivel atualizacao
        // make a clone with pre defined values  before a possible update
        localTempObj = objInternal
    }

    fun getIcon(): LiveData<Bitmap> {
        return icon
    }

    fun getFirstName(): LiveData<String> {
        return firstname
    }

    fun getPhoneticName(): LiveData<String> {
        return phoneticName
    }

    fun getLastName(): LiveData<String> {
        return lastname
    }

    fun getCompany(): LiveData<String> {
        return company
    }

    fun getPhone(): LiveData<String> {
        return phone
    }

    private fun fillEdits() {

        firstname.value = objInternal.contact.name
        phoneticName.value = objInternal.contact.phoneticName
        lastname.value = objInternal.contact.lastName
        company.value = objInternal.contact.company
        phone.value = objInternal.contact.phone

        val length = objInternal.photo.data?.size ?: 0
        icon.value = BitmapFactory.decodeByteArray(objInternal.photo.data, 0, length)

    }

    private fun fillObject(): ContactWithPhoto {

        if (icon.value == null) icon.value = Utils.defaultBitmap(context)
        else localTempObj.photo.data = Utils.bitmapToByteArray(icon.value!!)

        return localTempObj
    }

    private fun checkEdits(): Boolean {

        if (localTempObj.contact.name!!.isEmpty()) return false
        if (localTempObj.contact.phone!!.isEmpty()) return false

        return true

    }

    fun checkContactAndSave(): Boolean {

        if (objInternal.contact.id != null && objInternal.photo.id != null) {

            if (localTempObj.contact.id == objInternal.contact.id) {

                objInternal = fillObject()
                if (contactRepo.update(
                        objInternal.contact
                    ) && photoRepo.update(objInternal.photo)
                ) {

                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.message_updated_success),
                        Toast.LENGTH_LONG
                    )
                        .show()
                    return true
                }

            }

        } else {
            if (checkEdits()) {
                objInternal = fillObject()

                val photoId = photoRepo.insert(Photo(Utils.bitmapToByteArray(icon.value!!)))

                objInternal.contact.photoId = photoId
                contactRepo.insert(objInternal.contact)

                Toast.makeText(
                    context,
                    context.resources.getString(R.string.message_saved_success),
                    Toast.LENGTH_LONG
                ).show()

                return true

            } else {
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.message_empty_fields),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        return false

    }

    fun getObjInternal(): LiveData<ContactWithPhoto> {
        objInternalLiveData.value = objInternal
        return objInternalLiveData
    }

    fun onTextChange(editTextId: Int, text: String) {
        when (editTextId) {
            R.id.name -> localTempObj.contact.name = text
            R.id.phonetic -> localTempObj.contact.phoneticName = text
            R.id.lastName -> localTempObj.contact.lastName = text
            R.id.company -> localTempObj.contact.company = text
            R.id.phone_number -> localTempObj.contact.phone = text
        }
    }

}