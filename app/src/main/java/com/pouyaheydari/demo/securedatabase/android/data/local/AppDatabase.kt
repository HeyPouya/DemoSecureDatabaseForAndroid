package com.pouyaheydari.demo.securedatabase.android.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pouyaheydari.demo.securedatabase.android.data.security.SqlCipherKeyManager

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, sharedPreferences: SharedPreferences): AppDatabase {
            System.loadLibrary("sqlcipher")
            val sqlCipherKeyManager = SqlCipherKeyManager(sharedPreferences)
            val dbFile = context.getDatabasePath("your-db-name")
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    dbFile.absolutePath
                )
                    .openHelperFactory(sqlCipherKeyManager.getSupportFactory())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
