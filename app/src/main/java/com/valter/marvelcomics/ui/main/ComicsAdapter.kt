package com.valter.marvelcomics.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.valter.marvelcomics.R
import com.valter.marvelcomics.data.database.entity.Comic

class ComicsAdapter(
        private val onClickPerson: (comic: Comic) -> Unit
) : PagedListAdapter<Comic, ComicsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ComicsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_comic_list, parent, false),
            onClickPerson
    )

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Comic>() {
            override fun areItemsTheSame(
                    oldItem: Comic,
                    newItem: Comic
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                    oldItem: Comic,
                    newItem: Comic
            ) = oldItem == newItem
        }
    }
}