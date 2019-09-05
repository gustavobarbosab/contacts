package io.github.gustavobarbosab.contacts.base

import org.junit.Before
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest

abstract class BaseContactTest : AutoCloseKoinTest() {
    open val testModule: Module = module {  }

    @Before
    open fun before() {
        loadKoinModules(testModule)
    }
}