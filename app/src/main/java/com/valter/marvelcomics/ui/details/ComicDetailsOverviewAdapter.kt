package com.valter.marvelcomics.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.valter.marvelcomics.R
import com.valter.marvelcomics.data.model.Detail

class ComicDetailsOverviewAdapter : ListAdapter<Detail, ComicDetailsOverviewViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ComicDetailsOverviewViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_comic_details_overview, parent, false)
    )

    override fun onBindViewHolder(holder: ComicDetailsOverviewViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Detail>() {
            override fun areItemsTheSame(
                    oldItem: Detail,
                    newItem: Detail
            ) = oldItem.label == newItem.label

            override fun areContentsTheSame(
                    oldItem: Detail,
                    newItem: Detail
            ) = oldItem == newItem
        }
    }
}