package com.example.makeup.domain.repository

import com.example.makeup.data.api.MakeupAPI
import javax.inject.Inject

class MakeupRepository @Inject constructor(
    private val api: MakeupAPI
) {
    suspend fun getBrands() = api.getAllBrands()
    suspend fun getCategory(brand: String) = api.getAllCategory(brand)
    suspend fun getProduct(product: String) = api.getAllProduct(product)
}