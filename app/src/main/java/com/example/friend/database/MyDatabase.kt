package com.example.friend.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.friend.database.Friend

@Database(
    entities = [Friend::class],
    version = 1,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase(){

    abstract fun friendDao(): FriendDao

    companion object{
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            val tempInstace = INSTANCE
            if (tempInstace != null){
                return tempInstace
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, MyDatabase::class.java, "data_teman")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}