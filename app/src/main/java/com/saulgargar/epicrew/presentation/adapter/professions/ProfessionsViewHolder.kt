package com.saulgargar.epicrew.presentation.adapter.professions

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.profession_item.view.*

class ProfessionsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun renderView(professionItem : String){
        renderCard(professionItem = professionItem)
    }

    private fun renderCard(professionItem: String){
        itemView.run {
            professionTv.text = professionItem
        }
    }
}