package com.aprian1337.github_user.data.room

import android.content.Context
import android.media.tv.TvContract.AUTHORITY
import android.net.Uri
import android.service.notification.Condition.SCHEME
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomMasterTable.TABLE_NAME

@Database(entities = [FavoriteUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
        .authority(AUTHORITY)
        .appendPath(TABLE_NAME)
        .build()

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