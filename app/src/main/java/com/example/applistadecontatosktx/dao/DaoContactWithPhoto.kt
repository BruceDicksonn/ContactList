package com.example.applistadecontatosktx.dao

import androidx.room.*
import com.example.applistadecontatosktx.config.DatabaseConstants
import com.example.applistadecontatosktx.model.ContactWithPhoto

@Dao
interface DaoContactWithPhoto {

    @Insert
    fun insert(contactWithPhoto: ContactWithPhoto): Long

    @Update
    fun update(contactWithPhoto: ContactWithPhoto): Int

    @Delete
    fun delete(contactWithPhoto: ContactWithPhoto): Int

    @Query("SELECT * FROM ${DatabaseConstants.tables.contact} WHERE  id = :id")
    fun getByID(id: Long): ContactWithPhoto

    @Query("SELECT * FROM ${DatabaseConstants.tables.contact}")
    fun getAll(): List<ContactWithPhoto>

}