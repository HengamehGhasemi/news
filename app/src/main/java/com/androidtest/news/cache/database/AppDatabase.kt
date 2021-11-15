package com.androidtest.news.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androidtest.news.cache.dao.FavoritesNewsDao
import com.androidtest.news.cache.dao.NewsDao
import com.androidtest.news.cache.model.FavoritEntity
import com.androidtest.news.cache.model.NewsEntity

@Database(
    entities = [NewsEntity::class,FavoritEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao() : NewsDao
    abstract fun favoritesNewsDao() : FavoritesNewsDao

    companion object {
        private var instance: AppDatabase? = null
        @Synchronized
        fun get(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .build()
            }
            return instance!!
        }
    }
}