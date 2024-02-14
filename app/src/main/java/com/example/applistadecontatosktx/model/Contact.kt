package com.example.applistadecontatosktx.model

import androidx.room.*
import com.example.applistadecontatosktx.config.DatabaseConstants
import java.io.Serializable

@Entity(
    tableName = DatabaseConstants.tables.contact,
    foreignKeys = [
        ForeignKey(
            entity = Photo::class,
            parentColumns = ["id"],
            childColumns = ["photoId"]
        )
    ]
)
class Contact : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    var name: String? = ""
    var lastName: String? = ""
    var company: String? = ""
    var phone: String? = ""
    var photoId: Long? = null
    var phoneticName: String? = ""

    override fun toString(): String {
        return "$name $lastName"
    }


}

/**
 *
 *  To define relationships between entities, we must configure the ForeignKeys property.
 *  Its expected value is an array with ForeignKey instances. For each instance, we must define three values:
 *
 *  - entity: this property receives a value that represents the class entity for relationship;
 *  - parentColumns: receives an array representing the primary key columns of the parent entity;
 *  - childColumns: receives an array representing the foreign key columns of the current class entity;
 *
 *  We can have n foreign keys in our entity class
 *
 *
 *  Para definir relações entre entidades, devemos configurar a propriedade ForeignKeys.
 *  Seu valor esperado é um array com instâncias ForeignKey. Para cada instância, devemos definir três valores:
 *
 *  entity: esta propriedade recebe um valor que representa a entidade de classe para relacionamento
 *  parentColumns: recebe um array representando as colunas de chave primária da entidade pai
 *  childColumns: recebe um array representando as colunas de chave estrangeira da entidade de classe atual
 *
 *  Podemos ter n chaves estrangeiras em nossa classe de entidade
 *
 * **/