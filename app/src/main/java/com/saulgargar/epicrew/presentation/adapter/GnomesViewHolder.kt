package com.saulgargar.epicrew.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.saulgargar.androidext.setUrlCircle
import com.saulgargar.gnomedata.domain.model.GnomeUser
import kotlinx.android.synthetic.main.gnome_item.view.*

class GnomesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun renderView(gnomeItem : GnomeUser, onGnomeItemListener: (gnomeItem: GnomeUser) -> Unit){
       renderCard(gnomeItem = gnomeItem)
        setListener(gnomeItem = gnomeItem, onGnomeItemListener = onGnomeItemListener)
    }

    private fun renderCard(gnomeItem: GnomeUser){
        itemView.run {
            nameTv.text = gnomeItem.name

            professionTv.text = professionStringBuilder(gnomeItem.professions)

        }
    }

    private fun professionStringBuilder(professions: List<String>): String{
        var professionString = ""

        professions.forEachIndexed { i, profession ->
            when {
                i == 0 -> {
                    professionString += profession
                }
                i <= 2 -> {
                    professionString += ", $profession"
                }
                i == 3 -> {
                    professionString += " and more..."
                }
            }
        }

        return professionString
    }

    private fun setListener(gnomeItem: GnomeUser, onGnomeItemListener: (gnomeItem: GnomeUser) -> Unit) {
        itemView.setOnClickListener {
            onGnomeItemListener.invoke(gnomeItem)
        }
    }
}