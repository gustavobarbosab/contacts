package io.github.gustavobarbosab.contacts.ui.contacts.list

import io.github.gustavobarbosab.contacts.base.BaseContactTest
import io.github.gustavobarbosab.contacts.data.repository.ContactsRepository
import io.github.gustavobarbosab.contacts.ui.contacts.list.ContactListMockFactory.Companion.contactList
import io.github.gustavobarbosab.contacts.ui.contacts.list.ContactListMockFactory.Companion.emptyList
import io.github.gustavobarbosab.contacts.ui.contacts.list.ContactListMockFactory.Companion.errorEvent
import io.github.gustavobarbosab.contacts.util.LiveDataTestUtil.Companion.getLiveDataValue
import io.github.gustavobarbosab.contacts.utils.Result
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import java.lang.Exception

@ExperimentalCoroutinesApi
class ContactListViewModelTest : BaseContactTest() {

    private val repository = mockk<ContactsRepository>(relaxed = true)
    private lateinit var viewModel: ContactListViewModel

    override fun before() {
        viewModel = ContactListViewModel(repository)
    }

    @Test
    fun `When do not have contacts`() {
        // GIVEN
        mainCoroutineRule.pauseDispatcher()
        coEvery { repository.getContacts(any()) } returns Result.Success(emptyList)

        // WHEN
        viewModel.getContactList(true)

        // THEN
        assertTrue(getLiveDataValue(viewModel.dataLoading))
        mainCoroutineRule.resumeDispatcher()
        assertEquals(getLiveDataValue(viewModel.loadContacts), emptyList)
        assertNull(getLiveDataValue(viewModel.snackBarText))
        assertFalse(getLiveDataValue(viewModel.dataLoading))
    }

    @Test
    fun `When have contacts`() {
        // GIVEN
        mainCoroutineRule.pauseDispatcher()
        coEvery { repository.getContacts(any()) } returns Result.Success(contactList)

        // WHEN
        viewModel.getContactList(true)

        // THEN
        assertTrue(getLiveDataValue(viewModel.dataLoading))
        mainCoroutineRule.resumeDispatcher()
        assertEquals(getLiveDataValue(viewModel.loadContacts), contactList)
        assertNull(getLiveDataValue(viewModel.snackBarText))
        assertFalse(getLiveDataValue(viewModel.dataLoading))
    }

    @Test
    fun `When there is an error getting contacts`() {
        // GIVEN
        mainCoroutineRule.pauseDispatcher()
        coEvery { repository.getContacts(any()) } returns Result.Error(Exception())

        // WHEN
        viewModel.getContactList(true)

        // THEN
        assertTrue(getLiveDataValue(viewModel.dataLoading))
        mainCoroutineRule.resumeDispatcher()
        assertNull(getLiveDataValue(viewModel.loadContacts))
        assertSame(getLiveDataValue(viewModel.snackBarText), errorEvent)
        assertFalse(getLiveDataValue(viewModel.dataLoading))
    }
}