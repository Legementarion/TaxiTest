package com.lego.taxitest.domain

import androidx.lifecycle.LiveData
import com.lego.taxitest.domain.models.WalletTransaction

interface WalletRepository {

    fun getAllWalletTransactions(): LiveData<List<WalletTransaction>>

    suspend fun addTransaction(walletTransaction: WalletTransaction)

}