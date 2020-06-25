package com.formationandroid.projetfinalm1.bdd

import android.content.Context
import androidx.room.Room

class AppDatabaseHelper private constructor(context: Context) {
    private val database: AppDatabase

    companion object {
        // Attributs :
        private var databaseHelper: AppDatabaseHelper? = null

        // Getter instance :
        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            if (databaseHelper == null) {
                databaseHelper = AppDatabaseHelper(
                    context.applicationContext
                )
            }
            return databaseHelper!!.database
        }
    }

    // Constructeur :
    init {
        database = Room
            .databaseBuilder(context, AppDatabase::class.java, "courses.db")
            .allowMainThreadQueries()
            .build()
    }
}