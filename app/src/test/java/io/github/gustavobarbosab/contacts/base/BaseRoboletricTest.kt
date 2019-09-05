package io.github.gustavobarbosab.contacts.base

import android.app.Activity
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController

@RunWith(RobolectricTestRunner::class)
abstract class BaseRoboletricTest<T : Activity> : BaseContactTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var controller: ActivityController<T>
    protected lateinit var activity: T
    abstract val activityClass: Class<T>

    override fun before() {
        super.before()
        controller = Robolectric.buildActivity(activityClass)
    }

}