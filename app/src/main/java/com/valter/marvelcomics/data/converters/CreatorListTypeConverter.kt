package com.valter.marvelcomics.data.converters

import com.valter.marvelcomics.data.database.entity.CreatorList

class CreatorListTypeConverter :
        ComicObjectTypeConverter<CreatorList>(CreatorList::class.java)