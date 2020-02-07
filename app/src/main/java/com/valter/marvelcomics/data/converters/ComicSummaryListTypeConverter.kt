package com.valter.marvelcomics.data.converters

import com.valter.marvelcomics.data.database.entity.ComicSummary

class ComicSummaryListTypeConverter :
        ListTypeConverter<ComicSummary>(ComicSummary::class.java)