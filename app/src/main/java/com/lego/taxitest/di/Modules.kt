package com.lego.taxitest.di

import androidx.room.Room
import com.lego.taxitest.ui.wallet.WalletViewModel
import com.lego.taxitest.data.local.LocalDataSource
import com.lego.taxitest.data.local.LocalDataSourceImpl
import com.lego.taxitest.data.local.WalletDatabase
import com.lego.taxitest.domain.WalletRepository
import com.lego.taxitest.domain.WalletRepositoryImpl
import com.lego.taxitest.domain.usecase.AddWalletTransaction
import com.lego.taxitest.domain.usecase.GetAllWalletTransactions
import com.lego.taxitest.ui.deal.DealViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { WalletViewModel(get()) }
    viewModel { DealViewModel(get()) }
    single { GetAllWalletTransactions(get()) }
    single { AddWalletTransaction(get()) }
    single<WalletRepository> { WalletRepositoryImpl(get()) }
    single<LocalDataSource> { LocalDataSourceImpl(get()) }

    single {
        Room.databaseBuilder(
            androidApplication(),
            WalletDatabase::class.java,
            "database-taxi-transactions"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<WalletDatabase>().walletDao() }

}