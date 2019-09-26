package io.github.gustavobarbosab.contacts.ui.contacts.list

import android.Manifest
import android.os.Build
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import io.github.gustavobarbosab.contacts.R
import io.github.gustavobarbosab.contacts.base.BaseRobolectricTest
import io.github.gustavobarbosab.contacts.domain.ContactDto
import io.github.gustavobarbosab.contacts.ui.MainActivity
import io.github.gustavobarbosab.contacts.ui.contacts.list.ContactListMockFactory.Companion.FIRST_CONTACT_NAME
import io.github.gustavobarbosab.contacts.ui.contacts.list.ContactListMockFactory.Companion.SECOND_CONTACT_NAME
import io.github.gustavobarbosab.contacts.ui.contacts.list.ContactListMockFactory.Companion.contactList
import io.github.gustavobarbosab.contacts.ui.contacts.list.ContactListMockFactory.Companion.emptyList
import io.github.gustavobarbosab.contacts.util.getChildView
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.android.synthetic.main.content_contact_list.*
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.internal.LocalPermissionGranter
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
class ContactListFragmentTest : BaseRobolectricTest<MainActivity>() {

    override val activityClass: Class<MainActivity>
        get() = MainActivity::class.java

    override val testModule: Module
        get() = module(override = true) { viewModel { viewModel } }

    private val viewModel = spyk(ContactListViewModel(mockk()))

    private lateinit var liveData: MutableLiveData<List<ContactDto>>

    override fun before() {
        super.before()
        LocalPermissionGranter().apply {
            addPermissions(Manifest.permission.READ_CONTACTS)
            requestPermissions()
        }

        liveData = MutableLiveData()
        every { viewModel.getContactList(any()) } returns Unit
    }

    @Test
    @Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
    @Throws(Exception::class)
    fun `Given a user open the screen and has contacts`() {
        // GIVEN
        every { viewModel.loadContacts } returns liveData

        // WHEN
        activity = controller
            .setup()
            .get()

        liveData.value = contactList

        // THEN
        val recyclerView = activity.rvContactList
        val firstNameText = recyclerView.getChildView<TextView>(0, R.id.tvContactListName)
        val secondNameText = recyclerView.getChildView<TextView>(1, R.id.tvContactListName)

        assertEquals(FIRST_CONTACT_NAME, firstNameText?.text)
        assertEquals(SECOND_CONTACT_NAME, secondNameText?.text)
        assertEquals(contactList.size, recyclerView.childCount)
    }

    @Test
    @Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
    @Throws(Exception::class)
    fun `Given a user open the screen and has no contacts`() {
        // GIVEN
        every { viewModel.loadContacts } returns liveData

        // WHEN
        activity = controller
            .setup()
            .get()

        liveData.value = emptyList

        // THEN
        val recyclerView = activity.rvContactList
        assertEquals(emptyList.size, recyclerView.childCount)
    }


    @Test
    @Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
    @Throws(Exception::class)
    fun `Verify if view observe items and call load list`() {
        // GIVEN
        every { viewModel.loadContacts } returns liveData

        // WHEN
        activity = controller
            .create()
            .start()
            .get()

        liveData.value = emptyList

        // THEN
        verify {
            viewModel.loadContacts
            viewModel.snackBarTextMessage
        }
    }
}