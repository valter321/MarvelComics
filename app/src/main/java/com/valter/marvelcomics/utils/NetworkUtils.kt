package com.valter.marvelcomics.utils

import java.math.BigInteger
import java.security.MessageDigest

fun md5Hash(value: String) : String {
    return if(value.isNotEmpty())
        String.format("%032x",
                BigInteger(
                        1,
                        MessageDigest.getInstance("MD5").digest(value.toByteArray(Charsets.UTF_8))))
    else
        ""
}