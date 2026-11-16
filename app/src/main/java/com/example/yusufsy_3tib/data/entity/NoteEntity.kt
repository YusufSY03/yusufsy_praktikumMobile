package com.example.yusufsy_3tib.data.entity

import androidx.room.ColumnInfo // BARU
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val createdAt: Long = System.currentTimeMillis(), // DIUBAH: diakhiri koma
    @ColumnInfo(defaultValue = "0") // BARU
    val isPinned: Boolean = false // BARU
)