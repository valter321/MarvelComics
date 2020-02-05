package com.valter.marvelcomics.ui.main

import androidx.navigation.NavController
import com.valter.marvelcomics.R
import com.valter.marvelcomics.data.database.entity.Comic
import com.valter.marvelcomics.utils.safeNavigate

class ComicListNavigationImpl(
        private val navController: NavController
) : ComicListNavigation {

    override fun openComicDetails(comic: Comic) {
        navController.safeNavigate(0)
    }
}