package br.com.lira.rickandmorty.core.toolkit

import androidx.activity.addCallback
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import kotlin.reflect.KClass


private const val INITIAL_INDEX_POSITION = 0

fun Fragment.replaceFragmentHost(@IdRes hostRes: Int, fragment: Fragment) {
    parentFragmentManager.clearBackStack()
    parentFragmentManager.setupFragmentHost(hostRes, fragment)
}

fun AppCompatActivity.setupFragmentHost(@IdRes hostRes: Int, fragment: Fragment) {
    supportFragmentManager.setupFragmentHost(hostRes, fragment)
}

fun AppCompatActivity.hasBackStack(): Boolean {
    return supportFragmentManager.backStackEntryCount == INITIAL_INDEX_POSITION
}

fun Fragment.navigateToFragment(
    @IdRes hostRes: Int,
    destination: Fragment,
    allowStateLoss: Boolean = false,
    popBackTo: KClass<out Fragment>,
    flag: PopBackStackFlags = PopBackStackFlags.EXCLUSIVE
) {
    with(parentFragmentManager) {
        commitNavigation(
            hostRes = hostRes,
            fragment = destination,
            allowStateLoss = allowStateLoss,
            popBackTo = popBackTo.simpleName,
            flag = flag
        )
    }
}

fun Fragment.navigateToFragmentAndCleanStack(
    @IdRes hostRes: Int,
    destination: Fragment,
    allowStateLoss: Boolean = false,
) {
    val popBackToFirstTransition = parentFragmentManager.getBackStackEntryAt(INITIAL_INDEX_POSITION).name

    parentFragmentManager.commitNavigation(
        hostRes = hostRes,
        fragment = destination,
        allowStateLoss = allowStateLoss,
        popBackTo = popBackToFirstTransition,
        flag = PopBackStackFlags.INCLUSIVE,
    )
}

fun Fragment.navigateToFragment(
    @IdRes hostRes: Int,
    destination: Fragment,
    allowStateLoss: Boolean = false,
    fragmentAnimation: FragmentAnimation? = null
) {
    parentFragmentManager.commitNavigation(
        hostRes = hostRes,
        fragment = destination,
        allowStateLoss = allowStateLoss,
        fragmentAnimation = fragmentAnimation
    )
}

fun Fragment.popBackStack() {
    parentFragmentManager.popBackStack()
}

fun Fragment.clearBackStack() {
    parentFragmentManager.clearBackStack()
}

fun Fragment.addPopBackStackHandler() {
    requireActivity().onBackPressedDispatcher.addCallback(this) {
        popBackStack()
    }
}

fun FragmentManager.clearBackStack() {
    runCatching {
        val firstTransition = getBackStackEntryAt(INITIAL_INDEX_POSITION).name

        popBackStack(firstTransition, PopBackStackFlags.INCLUSIVE.value)
    }
}

fun FragmentManager.setupFragmentHost(@IdRes hostRes: Int, fragment: Fragment) {
    this.commitNavigation(hostRes, fragment, true)
}

@Suppress("LongParameterList")
fun FragmentManager.commitNavigation(
    @IdRes hostRes: Int,
    fragment: Fragment,
    allowStateLoss: Boolean,
    popBackTo: String? = null,
    flag: PopBackStackFlags = PopBackStackFlags.EXCLUSIVE,
    fragmentAnimation: FragmentAnimation? = null
) {
    commit(allowStateLoss) {
        fragmentAnimation?.setupAnimation(this)
        setReorderingAllowed(true)
        replace(hostRes, fragment, fragment::class.simpleName)
        popBackTo?.let { backFragmentDestination -> popBackStack(backFragmentDestination, flag.value) }
        addToBackStack(fragment::class.simpleName)
    }
}

enum class PopBackStackFlags(
    val value: Int
) {
    EXCLUSIVE(0),
    INCLUSIVE(1)
}