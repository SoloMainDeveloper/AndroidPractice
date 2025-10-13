package com.example.solomeinandroid.player.di

import com.example.solomeinandroid.player.data.PlayersApi
import com.example.solomeinandroid.player.data.mapper.PlayersResponseToEntityMapper
import com.example.solomeinandroid.player.data.repository.PlayersRepository
import com.example.solomeinandroid.player.domain.interactor.PlayerInteractor
import com.example.solomeinandroid.player.presentation.viewModel.PlayerDetailsViewModel
import com.example.solomeinandroid.player.presentation.viewModel.PlayersListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val playerFeatureModule = module {
    single { get<Retrofit>().create(PlayersApi::class.java) }

    factory { PlayersResponseToEntityMapper() }
    single { PlayersRepository(get(), get()) }

    single { PlayerInteractor(get()) }

    viewModel { PlayersListViewModel(get(), get()) }
    viewModel { PlayerDetailsViewModel(get(), get()) }
}