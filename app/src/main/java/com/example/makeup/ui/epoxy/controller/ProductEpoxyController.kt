package com.example.makeup.ui.epoxy.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.example.makeup.ui.epoxy.model.ProductEpoxyModel
import com.example.makeup.domain.model.MakeupItem

class ProductEpoxyController(
    private val onProductSelected: (MakeupItem) -> Unit
) : TypedEpoxyController<List<MakeupItem>>() {
    override fun buildModels(data: List<MakeupItem>?) {
        if (data == null || data.isEmpty()) {
            return
        }
        data.forEach { product ->
            ProductEpoxyModel(product, onProductSelected).id(product.id).addTo(this)
        }
    }
}