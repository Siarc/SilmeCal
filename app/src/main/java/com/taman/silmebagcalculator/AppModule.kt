package com.taman.silmebagcalculator

import com.taman.silmebagcalculator.datastore.NonWovenDataStore
import com.taman.silmebagcalculator.ui.screens.dashboard.DashboardViewModel
import com.taman.silmebagcalculator.ui.screens.login.LoginScreenViewModel
import com.taman.silmebagcalculator.ui.screens.nonwoven.NonWovenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { NonWovenDataStore(get()) }
    viewModel { DashboardViewModel() }
    viewModel { LoginScreenViewModel() }
    viewModel { NonWovenViewModel(get()) }
}
