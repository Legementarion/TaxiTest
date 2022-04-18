package com.lego.taxitest.domain.models

data class WalletTransaction(
    val id: Int = 0,
    val name: String,
    val value: String,
    val date: String,
    val type: DealType
)