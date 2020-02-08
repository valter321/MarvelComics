package com.valter.marvelcomics.ui.components

import androidx.paging.DataSource

typealias MarvelDataSourceBuilder<T> = () -> DataSource<String, T>
class MarvelDataSourceFactory<T>(private val builder: MarvelDataSourceBuilder<T>) : DataSource.Factory<String, T>() {

    override fun create(): DataSource<String, T> = builder()
}