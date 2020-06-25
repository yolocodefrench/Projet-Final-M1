package com.formationandroid.projetfinalm1.bdd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.formationandroid.projetfinalm1.objects.MemoDAO
import com.formationandroid.projetfinalm1.objects.MemoObject

@Database(entities = [MemoObject::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memosDAO(): MemoDAO?
}
