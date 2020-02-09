package com.valter.marvelcomics.ui.list

import androidx.navigation.NavController
import com.valter.marvelcomics.R
import com.valter.marvelcomics.ui.details.base.ComicDetailsFragmentArgs
import com.valter.marvelcomics.utils.safeNavigate

class ComicListNavigationImpl(
        private val navController: NavController
) : ComicListNavigation {

    override fun openComicDetails(comicId: String) {
        navController.safeNavigate(
                R.id.action_comicListFragment_to_ComicDetailsFragment,
                ComicDetailsFragmentArgs(comicId).toBundle()
        )
    }
}