package io.github.gustavobarbosab.contacts.data.source

import io.github.gustavobarbosab.contacts.domain.ContactDto
import io.github.gustavobarbosab.contacts.utils.Result

interface ContactsDataSource {
    fun getContacts(): Result<List<ContactDto>>
}