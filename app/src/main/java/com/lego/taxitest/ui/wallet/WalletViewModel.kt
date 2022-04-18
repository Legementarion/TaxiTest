package com.lego.taxitest.ui.wallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lego.taxitest.domain.models.WalletTransaction
import com.lego.taxitest.domain.usecase.GetAllWalletTransactions

class WalletViewModel(
    getAllWalletTransactions: GetAllWalletTransactions
) : ViewModel() {

    val walletTransactions: LiveData<List<WalletTransaction>> = getAllWalletTransactions.get()

}