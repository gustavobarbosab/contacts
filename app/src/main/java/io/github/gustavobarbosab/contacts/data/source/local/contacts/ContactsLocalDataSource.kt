package io.github.gustavobarbosab.contacts.data.source.local.contacts

import io.github.gustavobarbosab.contacts.data.source.ContactsDataSource
import io.github.gustavobarbosab.contacts.domain.ContactDto
import io.github.gustavobarbosab.contacts.utils.Result

class ContactsLocalDataSource(private val contactsLoader: ContactsLoader) : ContactsDataSource {

    override fun getContacts(): Result<List<ContactDto>> =
        Result.Success(contactsLoader.getContacts())
}