package io.github.gustavobarbosab.contacts.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest

abstract class BaseContactTest : AutoCloseKoinTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    open val testModule: Module = module {  }

    @Before
    open fun before() {
        loadKoinModules(testModule)
    }

    @After
    open fun after() {

    }
}