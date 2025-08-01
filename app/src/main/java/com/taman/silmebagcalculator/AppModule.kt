package com.taman.silmebagcalculator

import com.google.firebase.auth.FirebaseAuth
import com.taman.silmebagcalculator.datastore.NonWovenDataStore
import com.taman.silmebagcalculator.remote.AuthRepository
import com.taman.silmebagcalculator.remote.FirebaseAuthRepository
import com.taman.silmebagcalculator.ui.screens.dashboard.DashboardViewModel
import com.taman.silmebagcalculator.ui.screens.login.LoginScreenViewModel
import com.taman.silmebagcalculator.ui.screens.nonwoven.NonWovenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { NonWovenDataStore(androidContext()) }
    single { FirebaseAuth.getInstance() }
    single<AuthRepository> { FirebaseAuthRepository(get()) }
    viewModel { DashboardViewModel() }
    viewModel { LoginScreenViewModel(get()) }
    viewModel { NonWovenViewModel(get()) }
}
