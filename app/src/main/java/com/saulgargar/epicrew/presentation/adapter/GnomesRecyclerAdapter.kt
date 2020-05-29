package com.saulgargar.epicrew.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saulgargar.epicrew.R
import com.saulgargar.gnomedata.domain.model.GnomeUser

class GnomesRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var gnomeFinalList = listOf<GnomeUser>()
    private var gnomeList = listOf<GnomeUser>()
    lateinit var onGnomeItemListener: ((gnomeItem: GnomeUser) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.gnome_item, parent,
            ATTACH_ROOT
        )

        return GnomesViewHolder(view)
    }

    override fun getItemCount() = gnomeList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val gnomesViewHolder = holder as GnomesViewHolder
        gnomesViewHolder.renderView(gnomeList[position], onGnomeItemListener)
    }

    fun setGnomeList(gnomeList: List<GnomeUser>){
        this.gnomeFinalList = gnomeList
        this.gnomeList = gnomeList

        notifyDataSetChanged()
    }

    fun filterByQuery(query: String) {
        gnomeList = gnomeFinalList.filter { it.name.toLowerCase().contains(query) }
        notifyDataSetChanged()
    }

    private companion object {
        const val ATTACH_ROOT = false
    }
}