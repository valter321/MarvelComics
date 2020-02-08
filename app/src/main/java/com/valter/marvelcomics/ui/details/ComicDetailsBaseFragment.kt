package com.valter.marvelcomics.ui.details

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStoreOwner
import com.valter.marvelcomics.ui.components.BaseFragment
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * ComicDetailsBaseFragment
 *
 * All the [Fragment]s in the Comic Detail Page must extend this class.
 * Please check the [ComicDetailsFragment].
 */
abstract class ComicDetailsBaseFragment : BaseFragment() {

    protected val viewModel: ComicDetailsViewModel by sharedViewModel(from = { parentFragment as ViewModelStoreOwner })
}