package io.github.gustavobarbosab.contacts.ui

import android.os.Build
import androidx.lifecycle.MutableLiveData
import io.github.gustavobarbosab.contacts.domain.ContactDto
import io.github.gustavobarbosab.contacts.ui.contacts.list.ContactListFragment
import io.github.gustavobarbosab.contacts.ui.contacts.list.ContactListViewModel
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.stopKoin
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
class MainActivityTest : KoinTest {

    val viewModel: ContactListViewModel = mockk(relaxed = true)

    val myLiveData = MutableLiveData<List<ContactDto>>().apply {
        postValue(listOf(ContactDto(1, "", "", emptyList()), ContactDto(1, "", "", emptyList())))
    }

    private lateinit var activity: MainActivity
    private val controller: ActivityController<MainActivity> =
        Robolectric.buildActivity(MainActivity::class.java)

    @Before
    fun init() {
        koinApplication {
            module {
                viewModel { viewModel }
            }
        }
    }

    @After
    fun cleanUp() {
        stopKoin()
    }

    @Test
    @Throws(Exception::class)
    fun `Assert contact list fragment creation`() {

        every { viewModel.loadContacts } returns myLiveData

        activity = controller
            .create()
            .resume()
            .visible()
            .get()

        assertEquals(1, activity.supportFragmentManager.fragments.size)
        assertEquals(
            ContactListFragment::class.java.name,
            activity.supportFragmentManager.fragments.firstOrNull()?.javaClass?.name
        )
    }
}