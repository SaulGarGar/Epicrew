package com.saulgargar.epicrew.presentation.adapter.professions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saulgargar.epicrew.R

class ProfessionsRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    private var professionList = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.profession_item, parent,
            ATTACH_ROOT
        )

        return ProfessionsViewHolder(
            view
        )
    }

    override fun getItemCount() = professionList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val professionViewHolder = holder as ProfessionsViewHolder
        professionViewHolder.renderView(professionList[position])
    }

    fun setProfessionList(professionList: List<String>){
        this.professionList = professionList

        notifyDataSetChanged()
    }

    private companion object {
        const val ATTACH_ROOT = false
    }
}