package io.github.gustavobarbosab.contacts.ui

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.gustavobarbosab.contacts.BaseContactTest
import io.github.gustavobarbosab.contacts.data.repository.ContactsRepositoryImpl
import io.github.gustavobarbosab.contacts.domain.ContactDto
import io.github.gustavobarbosab.contacts.ui.contacts.list.ContactListFragment
import io.github.gustavobarbosab.contacts.ui.contacts.list.ContactListViewModel
import io.github.gustavobarbosab.contacts.utils.Result.Success
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
class MainActivityTest : BaseContactTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repository = mockk<ContactsRepositoryImpl>()

    lateinit var controller: ActivityController<MainActivity>

    private lateinit var activity: MainActivity

    override fun before() {
        super.before()
        controller = Robolectric.buildActivity(MainActivity::class.java)

    }

    @Test
    @Throws(Exception::class)
    fun `Assert contact list fragment creation`() {
        coEvery { repository.getContacts(any()) } returns Success(
            listOf(
                ContactDto(
                    0,
                    "",
                    "",
                    listOf()
                )
            )
        )

        controller.create().start().resume().visible().get()

        assertEquals(1, activity.supportFragmentManager.fragments.size)
        assertEquals(
            ContactListFragment::class.java.name,
            activity.supportFragmentManager.fragments.firstOrNull()?.javaClass?.name
        )
    }

    override val testModule: Module
        get() = module(override = true) {
            viewModel {
                ContactListViewModel(repository)
            }
        }
}