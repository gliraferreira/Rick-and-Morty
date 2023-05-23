package br.com.lira.rickandmorty.core.toolkit

import android.view.MotionEvent
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

fun EditText.setFocusWithKeyboard(shouldHasFocus: Boolean = false) {
    if (shouldHasFocus) {
        this.isEnabled = true
        this.requestFocus()
        this.showKeyboard()
    } else {
        this.clearFocus()
        this.hideKeyboard()
    }
}

fun RecyclerView.runWhenInteracted(block: () -> Unit) {
    addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent) = true

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
            block()
            removeOnItemTouchListener(this)
        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
    })
}