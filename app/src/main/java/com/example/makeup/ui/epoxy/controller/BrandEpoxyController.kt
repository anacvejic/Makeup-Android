package com.example.makeup.ui.epoxy.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.example.makeup.ui.epoxy.model.BrandEpoxyModel
import com.example.makeup.domain.model.MakeupItem

class BrandEpoxyController(
    private val onBrandSelected: (brand: String) -> Unit
) : TypedEpoxyController<List<MakeupItem>>() {
    override fun buildModels(data: List<MakeupItem>?) {
        if (data == null || data.isEmpty()) {
            return
        }
        data.forEach { brand ->
            BrandEpoxyModel(brand, onBrandSelected = onBrandSelected).id(brand.id).addTo(this)
        }
    }
}