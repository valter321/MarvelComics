package com.valter.marvelcomics.ui.details

import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.valter.marvelcomics.R
import com.valter.marvelcomics.data.model.ErrorData
import com.valter.marvelcomics.ui.components.BaseFragment
import com.valter.marvelcomics.utils.Outcome
import kotlinx.android.synthetic.main.fragment_comic_details.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ComicDetailsFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.fragment_comic_details

    private val args: ComicDetailsFragmentArgs by navArgs()

    private val viewModel: ComicDetailsViewModel by viewModel { parametersOf(args.comicId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vwpDetails.apply {
            orientation = ViewPager2.ORIENTATION_VERTICAL
            adapter = ComicDetailsAdapter(childFragmentManager, lifecycle)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.selectedComic.observe(viewLifecycleOwner, Observer {
            when (it) {
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

    private fun onRetryClicked() {
        viewModel.retry()
    }

    override fun onApplyWindowInsets(p0: View?, insets: WindowInsets): WindowInsets {
        return insets
    }
}