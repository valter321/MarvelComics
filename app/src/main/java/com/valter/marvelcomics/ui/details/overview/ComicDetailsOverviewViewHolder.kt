package com.valter.marvelcomics.ui.details.overview

import android.view.View
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.valter.marvelcomics.data.model.Detail
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_comic_details_overview.*

class ComicDetailsOverviewViewHolder(
        override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(detail: Detail) {
        with(detail) {
            txtLabel.text = label
            txtDescription.text = HtmlCompat.fromHtml(desc, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }
}
