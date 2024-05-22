package com.example.makeup.ui.epoxy.model

import coil.load
import com.example.makeup.R
import com.example.makeup.databinding.EpoxyProductCategoryItemsBinding
import com.example.makeup.ui.epoxy.ViewBindingKotlinModel
import com.example.makeup.domain.model.MakeupItem

data class ProductEpoxyModel(
    val product: MakeupItem,
    val onProductSelected: (MakeupItem) -> Unit
) : ViewBindingKotlinModel<EpoxyProductCategoryItemsBinding>(R.layout.epoxy_product_category_items) {
    override fun EpoxyProductCategoryItemsBinding.bind() {
        if (product.image_link != null) {
            productImage.load("${product.image_link}")
        } else {
            productImage.load(R.drawable.ic_noimage)
        }

        tvName.text = product.name.uppercase()
        tvBrandCategory.text = "${product.brand.uppercase()} ${product.category.uppercase()}"
        tvPrice.text = "$${product.price}"

        root.setOnClickListener {
            onProductSelected(product)
        }
    }
}
