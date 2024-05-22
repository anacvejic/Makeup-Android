package com.example.makeup.ui.epoxy.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.example.makeup.ui.epoxy.model.CategoryEpoxyModel
import com.example.makeup.domain.model.MakeupItem

class CategoryEpoxyController(
    private val onCategorySelected: (MakeupItem) -> Unit
) : TypedEpoxyController<List<MakeupItem>>() {
    override fun buildModels(data: List<MakeupItem>?) {
        if (data == null || data.isEmpty()) {
            return
        }
        data.forEach { category ->
            CategoryEpoxyModel(category, onCategorySelected).id(category.id).addTo(this)
        }
    }
}