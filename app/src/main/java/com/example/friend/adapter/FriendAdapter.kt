package com.example.friend.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.friend.R
import com.example.friend.database.Friend
import com.example.friend.databinding.ItemFriendBinding

class FriendAdapter(private val items: List<Friend>, private val onItemClick : (friend : Friend)-> Unit) : RecyclerView.Adapter<FriendViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        return FriendViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_friend, parent, false))
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onItemClick(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class FriendViewHolder(val viewDataBinding: ItemFriendBinding): RecyclerView.ViewHolder(viewDataBinding.root) {
    fun bind(friend: Friend?) {
        viewDataBinding.friend = friend
        viewDataBinding.executePendingBindings()
    }
}