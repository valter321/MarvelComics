package com.valter.marvelcomics.data.converters

import com.valter.marvelcomics.data.database.entity.ComicImage

class ImageTypeConverter :
        ComicObjectTypeConverter<ComicImage>(ComicImage::class.java)