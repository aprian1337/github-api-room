package com.aprian1337.github_user.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteUserDAO(): FavoriteUserDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "github_db"
                    ).build()
                }
                return INSTANCE
            }
            return INSTANCE
        }

        fun destroyDatabase() {
            INSTANCE = null
        }

    }
}