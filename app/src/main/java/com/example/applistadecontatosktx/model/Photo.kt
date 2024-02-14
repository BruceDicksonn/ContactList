package com.example.applistadecontatosktx.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.applistadecontatosktx.config.DatabaseConstants
import java.io.Serializable


@Entity(tableName = DatabaseConstants.tables.photo)
class Photo(var data: ByteArray?) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

}