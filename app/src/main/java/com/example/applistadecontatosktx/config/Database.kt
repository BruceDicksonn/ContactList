package com.example.applistadecontatosktx.config

import android.content.Context
import androidx.room.Room

class Database {

    companion object {

        private var db: DatabaseConfig? = null

        // singleton
        fun getInstance(context: Context): DatabaseConfig? {
            if (db == null) {

                synchronized(context.applicationContext) {
                    db = Room.databaseBuilder(
                         context,
                         DatabaseConfig::class.java,
                         DatabaseConstants.config.DB_NAME
                    )
                        // allows us to access database queries or functions without having to use asynchronous functions
                        // nos permite acessar consultas ou funções sem ter que usar funções assíncronas
                        .allowMainThreadQueries()
                        .build()
                }


            }

            return db
        }
    }

}

/**
 *
 * This is a mandatory class when we work with Room.
 * She is the one who gives access, builds or updates our database.
 * In it we need to have reference to the database configuration files
 *
 * Essa é uma classe obrigatória quando trabalhamos com o Room.
 * Ela que nos dá acesso, constrói ou atualiza nosso banco de dados.
 * Nela precisamos ter referência ao arquivos de configurações do banco de dados.
 *
 * **/