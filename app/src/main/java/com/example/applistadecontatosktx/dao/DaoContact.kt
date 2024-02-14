package com.example.applistadecontatosktx.dao

import androidx.room.*
import com.example.applistadecontatosktx.config.DatabaseConstants
import com.example.applistadecontatosktx.model.Contact
import com.example.applistadecontatosktx.model.ContactWithPhoto

@Dao
interface DaoContact {

    @Insert
    fun insert(contact: Contact): Long

    @Update
    fun update(contact: Contact): Int

    @Delete
    fun delete(contact: Contact): Int

    @Query("SELECT * FROM ${DatabaseConstants.tables.contact} WHERE  id = :id")
    fun getByID(id: Long): ContactWithPhoto

    @Query("SELECT * FROM ${DatabaseConstants.tables.contact}")
    fun getAll(): List<ContactWithPhoto>

}

/**
 *
 * Dao class is a mandatory requirement when working with Room.
 * Its structure basically consists of an interface with
 * each crud method containing its respective annotations
 *
 * A classe dao é um requisito obrigatório quando trabalhamos com a lib Room.
 * Sua estrutura consiste basicamente em uma interface com cada metodo do crud
 * contento sua respectiva anotação.
 *
 * **/