package com.example.yusufsy_3tib.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration // BARU
import androidx.sqlite.db.SupportSQLiteDatabase // BARU
import com.example.yusufsy_3tib.data.dao.NoteDao
import com.example.yusufsy_3tib.data.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 2, // DIUBAH: dari 1 menjadi 2
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // BARU: migrasi 1 ke 2, tambah kolom isPinned
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "ALTER TABLE notes " +
                            "ADD COLUMN isPinned INTEGER NOT NULL DEFAULT 0"
                )
            }
        }

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addMigrations(MIGRATION_1_2) // BARU
                    .build().also { INSTANCE = it }
            }
    }
}