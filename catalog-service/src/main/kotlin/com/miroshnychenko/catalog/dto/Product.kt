package com.miroshnychenko.catalog.dto

data class Product(val uniqId: String?, val sku: String?, val title: String?, val category: String?) {
    constructor() : this(null, null, null, null)
}