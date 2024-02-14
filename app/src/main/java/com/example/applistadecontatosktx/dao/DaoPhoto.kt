package com.example.applistadecontatosktx.dao

import androidx.room.*
import com.example.applistadecontatosktx.config.DatabaseConstants
import com.example.applistadecontatosktx.model.Photo

@Dao
interface DaoPhoto {

    @Insert
    fun insert(photo: Photo): Long

    @Update
    fun update(photo: Photo): Int

    @Delete
    fun delete(photo: Photo): Int

    @Query("SELECT * FROM ${DatabaseConstants.tables.photo} WHERE  id = :id")
    fun getByID(id: Int): Photo

    @Query("SELECT * FROM ${DatabaseConstants.tables.photo}")
    fun getAll(): List<Photo>

}