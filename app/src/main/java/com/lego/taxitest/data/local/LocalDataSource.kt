package com.lego.taxitest.data.local

import androidx.lifecycle.LiveData
import com.lego.taxitest.data.local.models.WalletTransaction

interface LocalDataSource {

    fun getAllTransactions(): LiveData<List<WalletTransaction>>

    fun addTransaction(transaction: WalletTransaction)

}