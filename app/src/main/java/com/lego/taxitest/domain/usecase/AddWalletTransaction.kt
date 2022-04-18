package com.lego.taxitest.domain.usecase

import com.lego.taxitest.domain.WalletRepository
import com.lego.taxitest.domain.models.WalletTransaction

class AddWalletTransaction(
    private val walletRepository: WalletRepository
) {

    suspend fun add(walletTransaction: WalletTransaction) {
        walletRepository.addTransaction(walletTransaction)
    }

}