package io.github.gustavobarbosab.contacts.data.repository

import io.github.gustavobarbosab.contacts.domain.ContactDto
import io.github.gustavobarbosab.contacts.utils.Result

interface ContactsRepository {
    suspend fun getContacts(force: Boolean): Result<List<ContactDto>>
}