package com.lego.taxitest.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.lego.taxitest.data.local.LocalDataSource
import com.lego.taxitest.domain.models.WalletTransaction
import com.lego.taxitest.domain.models.mapToDomain

class WalletRepositoryImpl(private val localDataSource: LocalDataSource) : WalletRepository {

    override fun getAllWalletTransactions(): LiveData<List<WalletTransaction>> {
        return localDataSource.getAllTransactions().map {
            it.map { transaction ->
                transaction.mapToDomain()
            }
        }
    }


    override suspend fun addTransaction(walletTransaction: WalletTransaction) {
        localDataSource.addTransaction(
            com.lego.taxitest.data.local.models.WalletTransaction(
                name = walletTransaction.name,
                value = walletTransaction.value,
                date = walletTransaction.date,
                type = walletTransaction.type
            )
        )
    }
}
