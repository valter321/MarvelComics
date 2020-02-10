package com.valter.marvelcomics.utils

import com.valter.marvelcomics.data.database.entity.ComicImage

const val IMAGE_VARIANT_NORMAL = "standard_xlarge"
const val IMAGE_VARIANT_HIGH_RES = "detail"

fun ComicImage.buildImageUrl() : String {
    return StringBuilder("${path}/$IMAGE_VARIANT_NORMAL.${extension}")
            .insert(4, "s")
            .toString()
}

fun ComicImage.buildHighResImageUrl() : String {
    return StringBuilder("${path}/$IMAGE_VARIANT_HIGH_RES.${extension}")
            .insert(4, "s")
            .toString()
}
