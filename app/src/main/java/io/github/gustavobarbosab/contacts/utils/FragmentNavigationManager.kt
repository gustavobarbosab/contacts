package io.github.gustavobarbosab.contacts.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import io.github.gustavobarbosab.contacts.R

fun FragmentManager.replace(
    newFragment: Fragment,
    container: Int = R.id.fragment_container,
    addBackStack: Boolean = false
) {
    this.beginTransaction().apply {
        this.replace(container, newFragment)
        if (addBackStack) this.addToBackStack(null)
        this.commit()
    }
}