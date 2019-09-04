package io.github.gustavobarbosab.contacts.data.source.remote.contacts

import io.github.gustavobarbosab.contacts.data.source.ContactsDataSource
import io.github.gustavobarbosab.contacts.domain.ContactDto
import io.github.gustavobarbosab.contacts.utils.Result

class ContactsRemoteDataSource : ContactsDataSource {
    override fun getContacts(): Result<List<ContactDto>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}