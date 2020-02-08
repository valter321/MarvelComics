package com.valter.marvelcomics.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.valter.marvelcomics.R
import com.valter.marvelcomics.data.database.entity.Comic
import com.valter.marvelcomics.data.model.ErrorData
import com.valter.marvelcomics.ui.main.components.BaseFragment
import com.valter.marvelcomics.utils.Outcome
import com.valter.marvelcomics.utils.insideValue
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val COLUMN_NUMBER = 3

@FlowPreview
@ExperimentalCoroutinesApi
class MainFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.main_fragment

    private val viewModel: MainViewModel by viewModel { parametersOf(this) }

    private val baseAdapter: ComicsAdapter by lazy { ComicsAdapter(::onComicClick) }

    private var previousSearchedWord = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Whenever the Search Edit Text changes a new query is sent to the viewModel
        tieSearch.addTextChangedListener { newQuery ->
            if(newQuery.toString() != previousSearchedWord) {
                viewModel.queryChannel.offer(newQuery.toString())
                previousSearchedWord = newQuery.toString()
            }
        }

        rclItems.apply {
            adapter = baseAdapter
            layoutManager = GridLayoutManager(context, COLUMN_NUMBER)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.comics.observe(viewLifecycleOwner, Observer {
            baseAdapter.submitList(it)
        })

        viewModel.comicData.observe(viewLifecycleOwner, Observer { outcome ->
            when (outcome) {
                is Outcome.Progress -> showLoading()
                is Outcome.Success -> showContent()
                is Outcome.Failure -> showError(
                        ErrorData(R.string.error_message,
                                R.string.retry
                        ),
                        ::onRetryClicked
                )
            }
        })
    }

    override fun onApplyWindowInsets(p0: View?, insets: WindowInsets): WindowInsets {
        return insets
    }

    private fun onComicClick(comic: Comic) {

    }

    private fun onRetryClicked() {
        viewModel.retry()
    }

    override fun onDestroyView() {
        rclItems.adapter = null
        super.onDestroyView()
    }

}
