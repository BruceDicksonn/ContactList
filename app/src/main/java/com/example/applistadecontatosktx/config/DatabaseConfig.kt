package com.example.applistadecontatosktx.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.applistadecontatosktx.dao.DaoContact
import com.example.applistadecontatosktx.dao.DaoPhoto
import com.example.applistadecontatosktx.model.Contact
import com.example.applistadecontatosktx.model.Photo

@Database(
    entities = [Contact::class, Photo::class],
    version = DatabaseConstants.config.DB_VERSION
)
abstract class DatabaseConfig : RoomDatabase() {
    abstract fun daoContact(): DaoContact
    abstract fun daoPhoto(): DaoPhoto
}


/**
 *
 *   This class defines the configuration and structure of our database. In it we need reference to our entities.
 *   Its definition is mandatory to comply with the Room standard.
 *
 *   Esta classe define a configuração e estrutura do nosso banco de dados. Nele precisamos de referência às nossas entidades.
 *   Sua definição é obrigatória para atendimento ao padrão Room.
 *
 *   Requirements to this class:
 *
 *   - Needs to be abstract and extend RoomDataBase;
 *   - Must provide functions that return Dao instances;
 *
 *    Requisitos para essa classe:
 *
 *   - Precisa ser abstrata e estender de RoomDatabase
 *   - Deve fornecer funções que retornam instâncias Dao
 *
 * **/
