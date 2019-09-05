package io.github.gustavobarbosab.contacts.ui

import android.os.Build
import io.github.gustavobarbosab.contacts.base.BaseRoboletricTest
import io.github.gustavobarbosab.contacts.ui.contacts.list.ContactListFragment
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Test
import org.robolectric.annotation.Config

class MainActivityTest : BaseRoboletricTest<MainActivity>() {

    override val activityClass: Class<MainActivity>
        get() = MainActivity::class.java

    @Test
    @Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
    @Throws(Exception::class)
    fun `When activity is created, assert actual fragment`() {
        // When
        activity = controller.create().start().resume().visible().get()

        // Then
        val fragmentVisible =
            activity.supportFragmentManager.fragments.firstOrNull()

        assertThat(fragmentVisible, `is`(instanceOf(ContactListFragment::class.java)))
    }
}