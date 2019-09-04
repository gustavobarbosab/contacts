package io.github.gustavobarbosab.contacts.data.repository

import androidx.lifecycle.LiveData
import io.github.gustavobarbosab.contacts.data.source.ContactsDataSource
import io.github.gustavobarbosab.contacts.domain.ContactDto

class ContactsRepositoryImpl(
    private val localDataSource: ContactsDataSource,
    val remoteDataSource: ContactsDataSource
) : ContactsRepository {
    override fun getContacts(): LiveData<List<ContactDto>> = localDataSource.getContacts()
}