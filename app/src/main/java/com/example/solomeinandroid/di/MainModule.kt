package com.example.solomeinandroid.di

import com.example.solomeinandroid.Players
import com.example.solomeinandroid.navigation.Route
import com.example.solomeinandroid.navigation.TopLevelBackStack
import com.example.solomeinandroid.player.viewModel.PlayerDetailsViewModel
import com.example.solomeinandroid.player.viewModel.PlayersListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { TopLevelBackStack<Route>(Players) }

    viewModel { PlayerDetailsViewModel(get(), get()) }
    viewModel { PlayersListViewModel(get()) }
}