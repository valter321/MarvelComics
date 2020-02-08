package com.valter.marvelcomics.ui.list

import com.valter.marvelcomics.data.database.entity.Comic

data class ComicLoadData(
        val comics: List<Comic>,
        val nextPage: String?
)
