package com.valter.marvelcomics.ui.main

import com.valter.marvelcomics.data.database.entity.Comic

interface ComicListNavigation {

    fun openComicDetails(comic: Comic)
}