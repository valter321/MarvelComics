package com.valter.marvelcomics.ui.main

import androidx.lifecycle.ViewModel
import com.valter.marvelcomics.data.repository.MarvelRepository
import com.valter.marvelcomics.dispatchers.DispatchersContainer

class MainViewModel(
        private val repository: MarvelRepository,
        private val dispatchersContainer: DispatchersContainer
) : ViewModel() {
    // TODO: Implement the ViewModel
}
