package com.valter.marvelcomics.ui.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.valter.marvelcomics.R
import com.valter.marvelcomics.data.database.entity.Comic
import com.valter.marvelcomics.utils.buildImageUrl
import com.valter.marvelcomics.utils.hide
import com.valter.marvelcomics.utils.setSingleClickListener
import com.valter.marvelcomics.utils.show
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_comic_list.*

const val IMAGE_NOT_AVAILABLE = "image_not_available"

class ComicsViewHolder(
        override val containerView: View,
        private val comicListener: (String) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(comic: Comic) {
        with(comic) {
            Glide.with(containerView.context)
                    .load(thumbnail.buildImageUrl())
                    .centerCrop()
                    .into(imgComic)

            if(thumbnail.path.contains(IMAGE_NOT_AVAILABLE)) {
                txtNotAvailable.text = title
                txtNotAvailable.show()
            } else {
                txtNotAvailable.hide()
            }

            ctlContent.setSingleClickListener { comicListener.invoke(id) }

        }
    }
}