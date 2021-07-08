package com.example.friend.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Friend(var name: String, var school: String, var hobby: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}