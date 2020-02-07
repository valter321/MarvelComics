package com.valter.marvelcomics.utils

import com.valter.marvelcomics.data.database.entity.ComicImage

const val IMAGE_VARIANT = "portrait_xlarge"

fun ComicImage.buildImageUrl() : String {
    return StringBuilder("${path}/$IMAGE_VARIANT.${extension}")
            .insert(4, "s")
            .toString()
}
