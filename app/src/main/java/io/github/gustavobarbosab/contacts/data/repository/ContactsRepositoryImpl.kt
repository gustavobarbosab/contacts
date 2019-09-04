package io.github.gustavobarbosab.contacts.data.repository

import io.github.gustavobarbosab.contacts.data.source.ContactsDataSource
import io.github.gustavobarbosab.contacts.domain.ContactDto
import io.github.gustavobarbosab.contacts.utils.Result.Success
import io.github.gustavobarbosab.contacts.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsRepositoryImpl(
    private val localDataSource: ContactsDataSource,
    val remoteDataSource: ContactsDataSource
) : ContactsRepository {

    private val _contacts: List<ContactDto> = emptyList()

    override suspend fun getContacts(): Result<List<ContactDto>> =
        withContext(Dispatchers.IO) {
            if (_contacts.isNotEmpty()) return@withContext Success( _contacts)
            localDataSource.getContacts()
        }
}