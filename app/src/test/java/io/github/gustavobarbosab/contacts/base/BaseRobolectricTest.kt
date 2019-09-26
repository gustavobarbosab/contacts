package io.github.gustavobarbosab.contacts.base

import android.app.Activity
import org.robolectric.Robolectric.buildActivity
import org.robolectric.android.controller.ActivityController

abstract class BaseRobolectricTest<T : Activity> : BaseContactTest() {

    lateinit var controller: ActivityController<T>
    protected lateinit var activity: T
    abstract val activityClass: Class<T>

    override fun before() {
        super.before()
        controller = buildActivity(activityClass)
    }
}