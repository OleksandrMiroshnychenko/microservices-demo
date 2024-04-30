package com.miroshnychenko.inventory.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ProductAvailability(val uniqId: String?, var availability: AvailabilityStatus?) {

    constructor() : this(null, null)
}