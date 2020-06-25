package com.formationandroid.projetfinalm1.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "memo_items")
class MemoObject(
    @ColumnInfo(name = "title")
    var title: String
){
    @Ignore()
    var selected: Boolean = false
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

}