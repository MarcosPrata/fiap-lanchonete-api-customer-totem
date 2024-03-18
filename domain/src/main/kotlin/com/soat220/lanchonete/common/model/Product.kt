package com.soat220.lanchonete.common.model

import com.soat220.lanchonete.common.model.enums.Category

class Product(
    var id: Long? = null,
    var name: String,
    var description: String,
    var category: Category,
    var price: Double,
    var imageUrls: List<String>,
    var deleted: Boolean
) {
    constructor() : this(null, "", "", Category.SIDE_DISH, 0.0, emptyList(), false)
}