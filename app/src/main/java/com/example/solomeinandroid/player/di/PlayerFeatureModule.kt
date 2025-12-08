package com.example.solomeinandroid.player.di

import androidx.datastore.core.DataStore
import com.example.solomeinandroid.player.data.PlayersApi
import com.example.solomeinandroid.player.data.mapper.PlayersResponseToEntityMapper
import com.example.solomeinandroid.player.data.repository.PlayersRepository
import com.example.solomeinandroid.player.domain.interactor.PlayerInteractor
import com.example.solomeinandroid.player.presentation.viewModel.PlayerDetailsViewModel
import com.example.solomeinandroid.player.presentation.viewModel.PlayerSettingsViewModel
import com.example.solomeinandroid.player.presentation.viewModel.PlayersListViewModel
import com.example.profile.data.entity.ProfileEntity
import com.example.profile.data.provider.DataSourceProvider
import com.example.profile.data.repository.ProfileRepository
import com.example.profile.presentation.viewModel.EditProfileViewModel
import com.example.profile.presentation.viewModel.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val playerFeatureModule = module {
    single { get<Retrofit>().create(PlayersApi::class.java) }

    factory { PlayersResponseToEntityMapper() }
    single { PlayersRepository(get(), get(), get(), get()) }

    single { PlayerInteractor(get()) }

    factory<DataStore<ProfileEntity>>(named("profile")) { DataSourceProvider(get()).provide() }
    single<ProfileRepository> { ProfileRepository() }

    viewModel { PlayersListViewModel(get(), get(), get()) }
    viewModel { PlayerDetailsViewModel(get(), get(), get()) }
    viewModel { PlayerSettingsViewModel(get(), get(), get()) }

    viewModel { ProfileViewModel(get()) }
    viewModel { EditProfileViewModel(get()) }
}