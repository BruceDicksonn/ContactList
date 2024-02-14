package com.example.applistadecontatosktx.repository

import android.content.Context
import com.example.applistadecontatosktx.config.Database
import com.example.applistadecontatosktx.model.Photo

class PhotoRepository(val context: Context) {

    private val daoPhoto = Database.getInstance(context)?.daoPhoto()

    fun insert(photo: Photo): Long {
        return daoPhoto?.insert(photo)?:-1
    }

    fun delete(photo: Photo): Boolean {
        return daoPhoto?.delete(photo)!! > 0
    }

    fun update(photo: Photo?): Boolean {
        return (photo != null && daoPhoto?.update(photo)!! > 0)
    }

    fun getById(id: Int): Photo {
        return daoPhoto?.getByID(id)!!
    }

    fun getAll(): List<Photo> {
        return daoPhoto?.getAll()!!
    }

}