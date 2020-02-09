package com.valter.marvelcomics

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.valter.marvelcomics.data.database.entity.Comic
import com.valter.marvelcomics.data.repository.MarvelRepository
import com.valter.marvelcomics.dispatchers.TestDispatcherContainer
import com.valter.marvelcomics.ui.list.ComicLoadData
import com.valter.marvelcomics.ui.list.MainViewModel
import com.valter.marvelcomics.utils.Outcome
import com.valter.marvelcomics.utils.blockingObserve
import com.valter.marvelcomics.utils.insideValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@FlowPreview
@ExperimentalCoroutinesApi
class VerticalViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        MockKAnnotations.init(this)
    }

    @Test
    fun `Loading a page, should broadcast the loaded people on the LiveData`() {
        val comicList = (1..10).map { mockk<Comic>(relaxed = true) }

        val mockedComicData = ComicLoadData(comicList, null)

        val repository: MarvelRepository = mockk {
            coEvery { getComics(any(), any()) } returns mockedComicData
        }

        val viewModel = MainViewModel(repository, TestDispatcherContainer)
        viewModel.comicData.blockingObserve()
        viewModel.comics.blockingObserve()

        assert(viewModel.comics.value!!.containsAll(comicList))
        with(viewModel.comicData) {
            assert(value is Outcome.Success)
            assert(value.insideValue.comics.containsAll(comicList))
        }
    }

    @Test
    fun `Loading one page and then another, should broadcast both pages on the LiveData`() {
        val pageList1 = (1..10).map { mockk<Comic>(relaxed = true) }

        val pageList2 = listOf(mockk<Comic>(relaxed = true))

        val repository: MarvelRepository = mockk {
            coEvery { getComics(any(), any()) } returns ComicLoadData(pageList1, "nextPage")
            coEvery { getComics(any(),"nextPage") } returns ComicLoadData(pageList2, null)
        }

        val viewModel = MainViewModel(repository, TestDispatcherContainer)

        viewModel.comics.blockingObserve()

        with(viewModel.comics.value!!) {
            assert(containsAll(pageList1)).also { loadAround(pageList1.size - 1) }
            assert(containsAll(pageList2))
            assert(size == pageList1.size + pageList2.size)
        }
    }

    @Test
    fun `If an error occurs when loading a page, this error should be triggered on the LiveData`() {
        val mockedError: Exception = mockk {
            every { printStackTrace() } returns Unit
        }

        val repository: MarvelRepository = mockk {
            coEvery { getComics(any(), any()) } throws mockedError
        }

        val viewModel = MainViewModel(repository, TestDispatcherContainer)

        viewModel.comics.blockingObserve()

        assert(viewModel.comicData.blockingObserve() is Outcome.Failure)
    }
}