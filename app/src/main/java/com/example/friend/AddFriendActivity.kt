package com.example.friend

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.friend.database.Friend
import com.example.friend.database.MyDatabase
import com.example.friend.databinding.ActivityAddFriendBinding
import java.util.concurrent.Executors

class AddFriendActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddFriendBinding

    private lateinit var database: MyDatabase

    var name = ""
    var school = ""
    var hobby = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_friend)
        binding.activity = this

        database = MyDatabase.getDatabase(this)
    }

    fun saveData(view: View?) {
        if (name.isNotEmpty() && school.isNotEmpty()&& hobby.isNotEmpty()) {
            val friend = Friend(name, school, hobby)
            Executors.newSingleThreadExecutor().execute {
                database.friendDao().insert(friend)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show()
            finish()

        }
    }
}