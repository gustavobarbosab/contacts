package io.github.gustavobarbosab.contacts.data.repository

import io.github.gustavobarbosab.contacts.data.source.ContactsDataSource
import io.github.gustavobarbosab.contacts.domain.ContactDto
import io.github.gustavobarbosab.contacts.utils.Result
import io.github.gustavobarbosab.contacts.utils.Result.Success
import io.github.gustavobarbosab.contacts.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsRepositoryImpl(
    private val localDataSource: ContactsDataSource,
    val remoteDataSource: ContactsDataSource
) : ContactsRepository {

    private var _cachedContacts: List<ContactDto> = emptyList()

    override suspend fun getContacts(force: Boolean): Result<List<ContactDto>> =
        wrapEspressoIdlingResource {
            withContext(Dispatchers.IO) {
                if (force.not() and _cachedContacts.isNotEmpty())
                    return@withContext Success(_cachedContacts)

                val result = localDataSource.getContacts()
                if (result is Success) {
                    _cachedContacts = result.data
                }
                result
            }
        }
}