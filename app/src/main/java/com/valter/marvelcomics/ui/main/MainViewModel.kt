package com.valter.marvelcomics.ui.main

import androidx.lifecycle.*
import androidx.paging.PageKeyedDataSource
import androidx.paging.toLiveData
import com.valter.marvelcomics.data.database.entity.Comic
import com.valter.marvelcomics.data.repository.MarvelRepository
import com.valter.marvelcomics.dispatchers.DispatchersContainer
import com.valter.marvelcomics.ui.main.components.MarvelDataSource
import com.valter.marvelcomics.ui.main.components.MarvelDataSourceFactory
import com.valter.marvelcomics.utils.Outcome
import com.valter.marvelcomics.utils.launchSafely

class MainViewModel(
        private val repository: MarvelRepository,
        private val dispatchersContainer: DispatchersContainer
) : ViewModel() {

    private val marvelDataSourceFactory = MarvelDataSourceFactory {
        MarvelDataSource(this::loadInitialPage, this::loadPage)
    }

    private var _comicData = MutableLiveData<Outcome<ComicLoadData>>()
    internal var comicData: LiveData<Outcome<ComicLoadData>> = _comicData
    internal var comics = marvelDataSourceFactory.toLiveData(1)

    /**
     * Proxy to MarvelDataSource
     */
    private fun loadInitialPage(callback: PageKeyedDataSource.LoadInitialCallback<String, Comic>) {
        launchLoadRequest(
                isFirstPage = true,
                pageToLoad = 0
        ) {
            callback.onResult(it.comics, null, it.nextPage.toString())
        }
    }

    /**
     * Proxy to MarvelDataSource
     */
    private fun loadPage(
            callback: PageKeyedDataSource.LoadCallback<String, Comic>,
            pageToLoad: String
    ) {
        launchLoadRequest(
                pageToLoad = pageToLoad.toInt()
        ) {
            callback.onResult(it.comics, it.nextPage.toString())
        }
    }

    private fun launchLoadRequest(
            isFirstPage: Boolean = false,
            pageToLoad: Int,
            dataSourceCallbackCaller: (ComicLoadData) -> Unit
    ) {
        viewModelScope.launchSafely(
                _comicData,
                loading = isFirstPage,
                context = dispatchersContainer.IO
        ) {
            repository.getComics(pageToLoad).also {
                dataSourceCallbackCaller(it)
            }
        }
    }

    fun retry() {
        comics.value?.dataSource?.invalidate()
    }
}
