package com.valter.marvelcomics.ui.list

import androidx.lifecycle.*
import androidx.paging.PageKeyedDataSource
import androidx.paging.toLiveData
import com.valter.marvelcomics.data.database.entity.Comic
import com.valter.marvelcomics.data.repository.MarvelRepository
import com.valter.marvelcomics.dispatchers.DispatchersContainer
import com.valter.marvelcomics.ui.components.MarvelDataSource
import com.valter.marvelcomics.ui.components.MarvelDataSourceFactory
import com.valter.marvelcomics.utils.Outcome
import com.valter.marvelcomics.utils.launchSafely
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

private const val QUERY_DEBOUNCE = 500L
private const val START_PAGE = "0"

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModel(
        private val repository: MarvelRepository,
        private val dispatchersContainer: DispatchersContainer
) : ViewModel() {

    // Registers when the launches
    var isFirstTime = true

    val queryChannel = ConflatedBroadcastChannel<String>()

    private val marvelDataSourceFactory = MarvelDataSourceFactory {
        MarvelDataSource(this::loadInitialPage, this::loadPage)
    }

    private var _comicData = MutableLiveData<Outcome<ComicLoadData>>()
    internal var comicData: LiveData<Outcome<ComicLoadData>> = _comicData
    internal var comics = marvelDataSourceFactory.toLiveData(1)

    init {
        queryChannel.asFlow()
                .debounce(QUERY_DEBOUNCE)
                .onEach { retry() }
                .launchIn(viewModelScope)
    }

    /**
     * Proxy to MarvelDataSource
     */
    private fun loadInitialPage(callback: PageKeyedDataSource.LoadInitialCallback<String, Comic>) {
        launchLoadRequest(
                isFirstPage = true
        ) {
            callback.onResult(it.comics, null, it.nextPage)
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
                pageToLoad = pageToLoad
        ) {
            callback.onResult(it.comics, it.nextPage)
        }
    }

    private fun launchLoadRequest(
            pageToLoad: String = START_PAGE,
            isFirstPage: Boolean = false,
            dataSourceCallbackCaller: (ComicLoadData) -> Unit
    ) {
        viewModelScope.launchSafely(
                _comicData,
                loading = isFirstPage,
                context = dispatchersContainer.IO
        ) {
            repository.getComics(queryChannel.valueOrNull.orEmpty(), pageToLoad).also {
                dataSourceCallbackCaller(it)
            }
        }
    }

    fun retry() {
        comics.value?.dataSource?.invalidate()
    }
}
