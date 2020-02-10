package com.valter.marvelcomics.utils

import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.valter.marvelcomics.R
import com.valter.marvelcomics.ui.components.SINGLE_LONG_CLICK_INTERVAL
import com.valter.marvelcomics.ui.components.SingleClickListener

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

/**
 *  Extension-function to prevent 'common double tap' issue
 *  when you're pressing button twice very fast and double action is called.
 */
fun View.setSingleClickListener(
        interval: Long = SINGLE_LONG_CLICK_INTERVAL,
        onSingleClick: (View) -> Unit
) {
    val singleClickListener = SingleClickListener(interval) {
        onSingleClick(it)
    }
    setOnClickListener(singleClickListener)
}

/**
 *  Hide Search Input when user scrolls down, and show it when the user scrolls up
 */
fun RecyclerView.addHideAndShowBehaviourTo(view: ViewGroup) {
    var checkScrollingUp = false
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy > 0) { // Scrolling up
                if (checkScrollingUp) {
                    view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.translation_upwards))
                    checkScrollingUp = false
                }
            } else { // User scrolls down
                if (!checkScrollingUp) {
                    view
                            .startAnimation(AnimationUtils
                                    .loadAnimation(context, R.anim.translation_downwards))
                    checkScrollingUp = true
                }
            }
        }
    })
}
