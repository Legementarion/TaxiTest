package com.lego.taxitest.domain.usecase

import androidx.lifecycle.LiveData
import com.lego.taxitest.domain.WalletRepository
import com.lego.taxitest.domain.models.WalletTransaction

class GetAllWalletTransactions(
    private val walletRepository: WalletRepository
) {

    fun get(): LiveData<List<WalletTransaction>> {
        return walletRepository.getAllWalletTransactions()
    }
}