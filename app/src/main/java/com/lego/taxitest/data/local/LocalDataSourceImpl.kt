package com.lego.taxitest.data.local

import androidx.lifecycle.LiveData
import com.lego.taxitest.data.local.models.WalletDao
import com.lego.taxitest.data.local.models.WalletTransaction

class LocalDataSourceImpl(private val walletDao: WalletDao): LocalDataSource {
    override fun getAllTransactions(): LiveData<List<WalletTransaction>> {
      return walletDao.getAll()
    }

    override fun addTransaction(transaction: WalletTransaction) {
        walletDao.insert(transaction)
    }
}