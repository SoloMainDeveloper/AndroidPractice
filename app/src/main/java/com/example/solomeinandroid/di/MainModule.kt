package com.example.solomeinandroid.di

import com.example.solomeinandroid.Players
import com.example.solomeinandroid.navigation.Route
import com.example.solomeinandroid.navigation.TopLevelBackStack
import org.koin.dsl.module

val mainModule = module {
    single { TopLevelBackStack<Route>(Players) }
}