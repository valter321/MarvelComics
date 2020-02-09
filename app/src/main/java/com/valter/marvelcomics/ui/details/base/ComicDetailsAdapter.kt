package com.valter.marvelcomics.ui.details.base

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.valter.marvelcomics.ui.details.header.ComicHeaderFragment
import com.valter.marvelcomics.ui.details.overview.ComicOverviewFragment

private const val NUMBER_OF_PAGES = 2

class ComicDetailsAdapter(
        fragmentManager: FragmentManager,
        lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = NUMBER_OF_PAGES

    override fun createFragment(position: Int) = when (ComicDetailPages.values()[position]) {
        ComicDetailPages.HEADER -> ComicHeaderFragment()
        ComicDetailPages.OVERVIEW -> ComicOverviewFragment()
    }
}