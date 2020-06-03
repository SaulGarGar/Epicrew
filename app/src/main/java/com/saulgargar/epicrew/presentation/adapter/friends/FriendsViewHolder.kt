package com.saulgargar.epicrew.presentation.adapter.friends

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.friend_item.view.*

class FriendsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    fun renderView(friendItem : String){
        renderCard(friendItem = friendItem)
    }

    private fun renderCard(friendItem: String){
        itemView.run {
            nameTv.text = friendItem
        }
    }
}