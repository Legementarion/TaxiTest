package com.lego.taxitest.data.local.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WalletDao {

    @Query("SELECT * FROM wallettransaction")
    fun getAll(): LiveData<List<WalletTransaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(walletTransaction: WalletTransaction)

}