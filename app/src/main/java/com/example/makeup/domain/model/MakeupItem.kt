package com.example.makeup.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class MakeupItem(
    @SerialName("brand")
    val brand: String,
    @SerialName("category")
    val category: String,
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: Int,
    @SerialName("image_link")
    val image_link: String,
    @SerialName("name")
    val name: String,
    @SerialName("price")
    val price: String,
    @SerialName("product_link")
    val product_link: String,
    @SerialName("product_type")
    val product_type: String
): Parcelable