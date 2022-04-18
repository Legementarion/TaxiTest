package com.lego.taxitest.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.lego.taxitest.domain.models.Converters
import com.lego.taxitest.domain.models.DealType

@Entity
data class WalletTransaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "value") val value: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "type")
    @TypeConverters(Converters::class)
    val type: DealType
)