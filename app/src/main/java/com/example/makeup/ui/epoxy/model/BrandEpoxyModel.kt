package com.example.makeup.ui.epoxy.model

import com.example.makeup.R
import com.example.makeup.databinding.EpoxyModelItemsBinding
import com.example.makeup.ui.epoxy.ViewBindingKotlinModel
import com.example.makeup.domain.model.MakeupItem

data class BrandEpoxyModel(
    val brand: MakeupItem,
    val onBrandSelected: (brand: String) -> Unit
) : ViewBindingKotlinModel<EpoxyModelItemsBinding>(R.layout.epoxy_model_items) {
    override fun EpoxyModelItemsBinding.bind() {
        tvItem.text = brand.brand

        root.setOnClickListener {
            onBrandSelected(brand.brand)
        }
    }
}
