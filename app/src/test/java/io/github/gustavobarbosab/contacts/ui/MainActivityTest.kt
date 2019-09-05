package io.github.gustavobarbosab.contacts.ui

import android.os.Build
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.gustavobarbosab.contacts.R
import io.github.gustavobarbosab.contacts.base.BaseRoboletricTest
import io.github.gustavobarbosab.contacts.data.repository.ContactsRepositoryImpl
import io.github.gustavobarbosab.contacts.domain.ContactDto
import io.github.gustavobarbosab.contacts.ui.contacts.list.ContactListViewModel
import io.github.gustavobarbosab.contacts.utils.Result.Success
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import org.robolectric.annotation.Config

class MainActivityTest : BaseRoboletricTest<MainActivity>() {

    override val activityClass: Class<MainActivity>
        get() = MainActivity::class.java

    private val repository = mockk<ContactsRepositoryImpl>()

    private val viewModel = ContactListViewModel(repository)

    @Test
    @Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
    @Throws(Exception::class)
    fun `Given an user open screen and there is contacts`() {
        // Given
        coEvery { repository.getContacts(any()) } returns Success(
            listOf(
                ContactDto(
                    1,
                    "",
                    FIRST_NAME,
                    listOf()
                ), ContactDto(
                    2,
                    "",
                    SECOND_NAME,
                    listOf()
                )
            )
        )

        // When
        activity = controller
            .create()
            .start()
            .resume()
            .visible()
            .get()

        // Then
        val recyclerView = activity.findViewById<RecyclerView>(R.id.rvContactList)
        val firstNameText = recyclerView
            .getChildAt(0)
            .findViewById<TextView>(R.id.tvContactListName)
        val secondNameText = recyclerView
            .getChildAt(1)
            .findViewById<TextView>(R.id.tvContactListName)

        assertEquals(FIRST_NAME, firstNameText.text)
        assertEquals(SECOND_NAME, secondNameText.text)
        assertEquals(RECYCLER_SIZE_WITH_ITEMS, recyclerView.childCount)
    }

    @Test
    @Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
    @Throws(Exception::class)
    fun `Given an user open screen and there is not contacts`() {
        // Given
        coEvery { repository.getContacts(any()) } returns Success(listOf())

        // When
        activity = controller
            .create()
            .start()
            .resume()
            .visible()
            .get()

        // Then
        val recyclerView = activity.findViewById<RecyclerView>(R.id.rvContactList)
        assertEquals(ZERO, recyclerView.childCount)
    }


    override val testModule: Module
        get() = module(override = true) { viewModel { viewModel } }

    companion object {
        const val FIRST_NAME = "Gustavo Barbosa"
        const val SECOND_NAME = "Gustavo Barbosa 2"
        const val RECYCLER_SIZE_WITH_ITEMS = 2
        const val ZERO = 0
    }
}