package com.saulgargar.epicrew.presentation.interfaces

import com.saulgargar.gnomedata.domain.model.GnomeUser

interface OnClickInGnome {
    fun onClickInGnomeListener(gnome: GnomeUser)
}