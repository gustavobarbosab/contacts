package io.github.gustavobarbosab.contacts

import org.junit.Before
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.test.AutoCloseKoinTest

abstract class BaseContactTest : AutoCloseKoinTest() {
    abstract val testModule: Module

    @Before
    open fun before() {
        loadKoinModules(testModule)
    }
}