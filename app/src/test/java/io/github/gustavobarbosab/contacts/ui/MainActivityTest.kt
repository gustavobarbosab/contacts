package io.github.gustavobarbosab.contacts.ui

import android.os.Build
import io.github.gustavobarbosab.contacts.base.BaseRobolectricTest
import io.github.gustavobarbosab.contacts.ui.contacts.list.ContactListFragment
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
class MainActivityTest : BaseRobolectricTest<MainActivity>() {

    override val activityClass: Class<MainActivity>
        get() = MainActivity::class.java

    @Test
    @Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
    @Throws(Exception::class)
    fun `When activity is created, assert actual fragment`() {
        // WHEN
        activity = controller
            .setup()
            .get()

        // THEN
        val fragmentVisible =
            activity.supportFragmentManager.fragments.firstOrNull()

        assertThat(fragmentVisible, `is`(instanceOf(ContactListFragment::class.java)))
    }
}