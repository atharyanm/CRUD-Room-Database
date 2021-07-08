package com.example.friend

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.friend.database.Friend
import com.example.friend.database.MyDatabase
import com.example.friend.databinding.ActivityEditFriendBinding
import java.util.concurrent.Executors

class EditFriendActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditFriendBinding
    private lateinit var database: MyDatabase
    var idFriend = 0
    var name = ""
    var school = ""
    var hobby = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_friend)
        binding.activity = this

        idFriend = intent.getIntExtra("id", 0)
        name = intent.getStringExtra("name") ?: ""
        school = intent.getStringExtra("school") ?: ""
        hobby = intent.getStringExtra("hobby") ?: ""

        database = MyDatabase.getDatabase(this)
    }

    fun editData(view: View?) {
        if (name.isNotEmpty() && school.isNotEmpty() && hobby.isNotEmpty()) {
            val friend = Friend(name, school, hobby).apply {
                id = idFriend
            }
            Executors.newSingleThreadExecutor().execute {
                database.friendDao().update(friend)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    fun deleteData(view: View?) {
        val friend = Friend(name, school, hobby).apply {
            id = idFriend
        }
        Executors.newSingleThreadExecutor().execute {
            database.friendDao().delete(friend)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        Toast.makeText(this, "Data has been deleted", Toast.LENGTH_LONG).show()
        finish()
    }
}
