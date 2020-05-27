package com.saulgargar.gnomedata.data.repository

import com.saulgargar.gnomedata.data.datasource.local.entity.HairColorDB
import com.saulgargar.gnomedata.data.datasource.local.entity.ProfessionDB
import com.saulgargar.gnomedata.data.datasource.remote.model.GnomeInfoItemResponse
import com.saulgargar.gnomedata.domain.model.HairColor
import com.saulgargar.gnomedata.domain.model.Profession

object ExtraDataCompiler {
    fun getAllProfessions(gnomes: List<GnomeInfoItemResponse>): MutableList<Profession>{
        val professions = mutableListOf<String>()

        for (gnome in gnomes){
            for (profession in gnome.professions){
                professions.add(profession)
            }
        }

        val professionObj = mutableListOf<Profession>()

        professions.distinct().forEachIndexed { i, profession ->
            professionObj.add(Profession(i,profession))
        }

        return professionObj
    }

    fun getAllHairColors(gnomes: List<GnomeInfoItemResponse>): MutableList<HairColor>{
        val colors = mutableListOf<String>()

        for (gnome in gnomes){
            colors.add(gnome.hairColor)
        }

        val professionObj = mutableListOf<HairColor>()

        colors.distinct().forEachIndexed { i, profession ->
            professionObj.add(HairColor(i,profession))
        }

        return professionObj
    }
}