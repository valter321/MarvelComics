package com.valter.marvelcomics.data.converters

import com.valter.marvelcomics.data.database.entity.CreatorSummary

class CreatorSummaryListTypeConverter :
        ListTypeConverter<CreatorSummary>(CreatorSummary::class.java)