package io.github.gustavobarbosab.contacts.data.repository

import androidx.lifecycle.LiveData
import io.github.gustavobarbosab.contacts.domain.ContactDto

interface ContactsRepository {
    fun getContacts(): LiveData<List<ContactDto>>
}