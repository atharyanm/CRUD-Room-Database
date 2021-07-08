package com.example.friend

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.friend.adapter.FriendAdapter
import com.example.friend.database.MyDatabase
import com.example.friend.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var database: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        database = MyDatabase.getDatabase(this)

        database.friendDao().getAll().observe(this, {
            binding.adapter = FriendAdapter(it) {
                val intent = Intent(this, EditFriendActivity::class.java).apply {
                    putExtra("id", it.id)
                    putExtra("name", it.name)
                    putExtra("school", it.school)
                    putExtra("hobby", it.hobby)
                }
                startActivity(intent)
            }
        })
    }

    fun addData(view: View?) {
        val intent = Intent(this, AddFriendActivity::class.java)
        startActivity(intent)
    }
}