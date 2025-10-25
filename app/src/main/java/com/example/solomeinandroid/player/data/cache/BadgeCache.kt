package com.example.solomeinandroid.player.data.cache

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BadgeCache {
    private val _hasActiveFilters = MutableStateFlow(false)
    val hasActiveFilters: StateFlow<Boolean> = _hasActiveFilters.asStateFlow()

    fun setFiltersActive(active: Boolean) {
        _hasActiveFilters.value = active
    }

    fun hasActiveFilters(): Boolean = _hasActiveFilters.value
}