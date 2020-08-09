package com.studyfork.sfoide.presentation.extensions

import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

inline fun <reified T : Fragment> FragmentManager.replaceFragment(
    @IdRes containerViewId: Int,
    addToBackStack: Boolean = false,
    vararg params: Pair<String, Any?>,
    tag: String = T::class.java.simpleName
): T? {
    return findFragment() ?: T::class.java.newInstance().also { newFragment ->
        newFragment.arguments = bundleOf(*params)
        beginTransaction()
            .replace(containerViewId, newFragment, tag).apply {
                if (addToBackStack) addToBackStack(null)
            }
            .commitAllowingStateLoss()
    }
}

inline fun <reified T> FragmentManager.findFragment(
    tag: String = T::class.java.simpleName
): T? {
    return findFragmentByTag(tag) as? T
}