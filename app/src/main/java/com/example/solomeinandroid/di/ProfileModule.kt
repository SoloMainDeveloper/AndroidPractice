package com.example.solomeinandroid.di

import androidx.datastore.core.DataStore
import com.example.solomeinandroid.profile.data.entity.ProfileEntity
import com.example.solomeinandroid.profile.data.provider.DataSourceProvider
import com.example.solomeinandroid.profile.data.repository.ProfileRepository
import com.example.solomeinandroid.profile.presentation.viewModel.EditProfileViewModel
import com.example.solomeinandroid.profile.presentation.viewModel.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val profileModule = module {
    factory<DataStore<ProfileEntity>>(named("profile")) { DataSourceProvider(get()).provide() }
    single<ProfileRepository> { ProfileRepository() }

    viewModel { ProfileViewModel(get()) }
    viewModel { EditProfileViewModel(get()) }
}