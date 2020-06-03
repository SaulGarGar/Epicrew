package com.saulgargar.epicrew.presentation.adapter.friends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saulgargar.epicrew.R

class FriendsRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var friendList = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.friend_item, parent,
            ATTACH_ROOT
        )

        return FriendsViewHolder(
            view
        )
    }

    override fun getItemCount() = friendList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val friendsViewHolder = holder as FriendsViewHolder
        friendsViewHolder.renderView(friendList[position])
    }

    fun setFriendList(friendList: List<String>){
        this.friendList = friendList

        notifyDataSetChanged()
    }

    private companion object {
        const val ATTACH_ROOT = false
    }
}