package io.github.gustavobarbosab.contacts.data.source.local.contacts

import androidx.lifecycle.LiveData
import io.github.gustavobarbosab.contacts.data.source.ContactsDataSource
import io.github.gustavobarbosab.contacts.domain.ContactDto

class ContactsLocalDataSource(private val contactsLoader: ContactsLoader) : ContactsDataSource {

    override fun getContacts(): LiveData<List<ContactDto>> = contactsLoader.getContacts()
}