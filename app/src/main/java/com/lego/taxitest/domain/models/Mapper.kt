package com.lego.taxitest.domain.models

import androidx.room.TypeConverter
import com.lego.taxitest.data.local.models.WalletTransaction

fun WalletTransaction.mapToDomain() = com.lego.taxitest.domain.models.WalletTransaction(
    id = id,
    name = name,
    value = value,
    date = date,
    type = type
)

class Converters {

    @TypeConverter
    fun toType(value: Int) = enumValues<DealType>()[value]

    @TypeConverter
    fun fromType(value: DealType) = value.ordinal

}