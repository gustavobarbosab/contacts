package io.github.gustavobarbosab.contacts.data.source

import androidx.lifecycle.LiveData
import io.github.gustavobarbosab.contacts.domain.ContactDto

interface ContactsDataSource {
    fun getContacts(): LiveData<List<ContactDto>>
}