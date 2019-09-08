package io.github.gustavobarbosab.contacts.ui.contacts.list

import io.github.gustavobarbosab.contacts.R
import io.github.gustavobarbosab.contacts.domain.ContactDto
import io.github.gustavobarbosab.contacts.utils.Event

class ContactListMockFactory {
    companion object {

        val errorEvent
                get() = R.string.error_load_contacts

        val emptyList: List<ContactDto> = emptyList()

        val contactList: List<ContactDto>
            get() = listOf(this.contactOne, contactTwo)

        val contactOne: ContactDto
            get() = ContactDto(
                2,
                "",
                FIRST_CONTACT_NAME,
                listOf()
            )

        val contactTwo: ContactDto
            get() = ContactDto(
                2,
                "",
                SECOND_CONTACT_NAME,
                listOf()
            )

        const val FIRST_CONTACT_NAME = "Gustavo Barbosa"
        const val SECOND_CONTACT_NAME = "Gustavo Barbosa 2"
    }
}