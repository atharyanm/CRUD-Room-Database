package com.example.friend.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.friend.database.Friend

@Dao
interface FriendDao {
    @Insert
    fun insert(friend: Friend)

    @Query("SELECT * FROM Friend")
    fun getAll(): LiveData<List<Friend>>

    @Update
    fun update(friend: Friend)

    @Delete
    fun delete(friend: Friend)
}