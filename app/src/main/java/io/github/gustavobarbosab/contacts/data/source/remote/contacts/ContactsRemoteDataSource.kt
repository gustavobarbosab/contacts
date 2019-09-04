package io.github.gustavobarbosab.contacts.data.source.remote.contacts

import androidx.lifecycle.LiveData
import io.github.gustavobarbosab.contacts.data.source.ContactsDataSource
import io.github.gustavobarbosab.contacts.domain.ContactDto

class ContactsRemoteDataSource: ContactsDataSource {
    override fun getContacts(): LiveData<List<ContactDto>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}