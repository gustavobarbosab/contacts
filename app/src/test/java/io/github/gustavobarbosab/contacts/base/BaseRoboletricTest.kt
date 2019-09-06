package io.github.gustavobarbosab.contacts.base

import android.app.Activity
import org.junit.runner.RunWith
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController

@RunWith(RobolectricTestRunner::class)
abstract class BaseRoboletricTest<T : Activity> : BaseContactTest() {

    lateinit var controller: ActivityController<T>
    protected lateinit var activity: T
    abstract val activityClass: Class<T>

    override fun before() {
        super.before()
        controller = buildActivity(activityClass)
    }
}