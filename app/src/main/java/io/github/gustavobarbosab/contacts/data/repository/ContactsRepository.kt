package io.github.gustavobarbosab.contacts.data.repository

import io.github.gustavobarbosab.contacts.domain.ContactDto
import io.github.gustavobarbosab.contacts.utils.Result

interface ContactsRepository {
    // TODO fazer o retorno de outro model, deixando o ContactDto fora da camada de view
    suspend fun getContacts(force: Boolean): Result<List<ContactDto>>
}