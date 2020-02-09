package com.valter.marvelcomics

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.valter.marvelcomics.data.database.entity.Comic
import com.valter.marvelcomics.data.repository.MarvelRepository
import com.valter.marvelcomics.dispatchers.TestDispatcherContainer
import com.valter.marvelcomics.ui.details.base.ComicDetailAction
import com.valter.marvelcomics.ui.details.base.ComicDetailsViewModel
import com.valter.marvelcomics.utils.Outcome
import com.valter.marvelcomics.utils.blockingObserve
import com.valter.marvelcomics.utils.insideValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@FlowPreview
@ExperimentalCoroutinesApi
class ComicDetailsViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        MockKAnnotations.init(this)
    }

    @Test
    fun `Fetching a single comic , should broadcast the comic data into the LiveData`() {
        val mockComic = mockk<Comic>(relaxed = true)

        val repository: MarvelRepository = mockk {
            coEvery { fetchComic(any()) } returns mockComic
        }

        val viewModel = ComicDetailsViewModel(repository, TestDispatcherContainer, "")
        viewModel.selectedComic.blockingObserve()

        with(viewModel.selectedComic) {
            assert(value is Outcome.Success)
            assert(value.insideValue == mockComic)
        }
    }

    @Test
    fun `When next action is called, it should propagate an Outcome_Success to the action LiveData`() = runBlockingTest {
        val viewModel = ComicDetailsViewModel(mockk(relaxed = true), TestDispatcherContainer, "")

        viewModel.openNext()

        assert(viewModel.action.value == Outcome.success(ComicDetailAction.Next))
    }
}