package com.saulgargar.epicrew.presentation.model

import java.io.Serializable

class PhysicalFeaturesQuery: Serializable {
    var minAge = 0
    var maxAge = 100

    var minWeight = 0
    var maxWeight = 100

    var minHeight = 0
    var maxHeight = 100

    var hairColor: String? = null
}