package com.valter.marvelcomics.data.converters

import com.valter.marvelcomics.data.database.entity.ComicImage

class ImageListTypeConverter :
        ListTypeConverter<ComicImage>(ComicImage::class.java)