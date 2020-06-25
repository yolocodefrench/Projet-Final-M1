package com.formationandroid.projetfinalm1.objects

import androidx.room.*

@Dao
interface MemoDAO {
    @Query("SELECT * FROM memo_items")
    fun getAll(): List<MemoObject>

    @Query("SELECT * FROM memo_items WHERE title LIKE :title")
    fun findByTitle(title: String): MemoObject

    @Insert
    fun insertAll(vararg memo: MemoObject)

    @Delete
    fun delete(memo: MemoObject)

    @Update
    fun updateMemo(vararg memos: MemoObject)
}