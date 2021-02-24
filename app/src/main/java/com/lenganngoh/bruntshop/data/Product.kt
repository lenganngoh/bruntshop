package com.lenganngoh.bruntshop.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "product")
data class Product(
    @PrimaryKey
    val id: String = "",
    val name: String? = "",
    val category: String? = "",
    val price: String? = "",
    val bgColor: String? = ""
): Parcelable