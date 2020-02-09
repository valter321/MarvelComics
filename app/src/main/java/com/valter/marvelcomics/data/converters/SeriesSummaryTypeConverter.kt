package com.valter.marvelcomics.data.converters

import com.valter.marvelcomics.data.database.entity.SeriesSummary

class SeriesSummaryTypeConverter :
        ComicObjectTypeConverter<SeriesSummary>(SeriesSummary::class.java)