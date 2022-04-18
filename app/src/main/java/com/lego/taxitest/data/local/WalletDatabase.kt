package com.lego.taxitest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lego.taxitest.data.local.models.WalletDao
import com.lego.taxitest.data.local.models.WalletTransaction

@Database(entities = [WalletTransaction::class], version = 1)
abstract class WalletDatabase : RoomDatabase() {
    abstract fun walletDao(): WalletDao
}