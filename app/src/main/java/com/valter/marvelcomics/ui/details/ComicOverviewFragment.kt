package com.valter.marvelcomics.ui.details

import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import androidx.lifecycle.Observer
import com.valter.marvelcomics.R
import com.valter.marvelcomics.data.database.entity.Comic
import com.valter.marvelcomics.data.model.Detail
import com.valter.marvelcomics.utils.Outcome
import kotlinx.android.synthetic.main.fragment_comic_details_overview.*

class ComicOverviewFragment : ComicDetailsBaseFragment() {
    override val layout: Int
        get() = R.layout.fragment_comic_details_overview

    private val baseAdapter = ComicDetailsOverviewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedComic.observe(viewLifecycleOwner, Observer { outcome ->
            when (outcome) {
                is Outcome.Success -> setData(outcome.data)
            }
        })
    }

    private fun setData(comic: Comic) {
        val details = mutableListOf<Detail>()

        with(comic) {


            details.fillDetails(this)
        }

        rclOverview.apply {
            adapter = baseAdapter
            baseAdapter.submitList(details)
        }
    }

    override fun onDestroyView() {
        rclOverview.adapter = null
        super.onDestroyView()
    }

    override fun onApplyWindowInsets(p0: View?, p1: WindowInsets?) = p1

    private fun MutableList<Detail>.fillDetails(comic: Comic) {
        add(Detail(getString(R.string.details_title), comic.title))
        add(Detail(getString(R.string.details_creators), comic.creators.items.joinToString { "${it.name} (${it.role})" }))
        add(Detail(getString(R.string.details_series_name), comic.series.name))
        add(Detail(getString(R.string.details_issue_number), comic.issueNumber))
        if (comic.description != null) {
            add(Detail(getString(R.string.details_description), comic.description))
        }
    }
}