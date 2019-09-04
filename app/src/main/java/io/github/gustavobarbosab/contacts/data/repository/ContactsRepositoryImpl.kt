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

    private var _contacts: List<ContactDto> = emptyList()

    override suspend fun getContacts(force: Boolean): Result<List<ContactDto>> =
        wrapEspressoIdlingResource {
            withContext(Dispatchers.IO) {

                if (force.not() and _contacts.isNotEmpty())
                    return@withContext Success(_contacts)

                val result = localDataSource.getContacts()
                if (result is Success) {
                    _contacts = result.data
                }
                result
            }
        }
}