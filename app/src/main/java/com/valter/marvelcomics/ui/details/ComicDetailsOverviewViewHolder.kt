package com.valter.marvelcomics.ui.details

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.valter.marvelcomics.data.model.Detail
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_comic_details_overview.*

const val TYPE_COLOR_DETAILS = "400"

class ComicDetailsOverviewViewHolder(
        override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(detail: Detail) {
        with(detail) {
            txtLabel.text = label
            txtDescription.text = desc
        }
    }
}
