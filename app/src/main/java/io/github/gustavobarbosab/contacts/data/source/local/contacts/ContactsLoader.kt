package io.github.gustavobarbosab.contacts.data.source.local.contacts

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import io.github.gustavobarbosab.contacts.domain.ContactDto
import kotlinx.coroutines.Dispatchers

class ContactsLoader(private val cursorCreator: ContactCursorCreator) {

    fun getContacts(
        name: String? = null
    ): LiveData<List<ContactDto>> =
        liveData(Dispatchers.IO) {
            emit(getContactsInfo(name))
        }

    private fun getContactsInfo(name: String? = null): List<ContactDto> =
        cursorCreator
            .createCursorToContactsInfo(name)
            ?.let { cursor ->
                val contacts: List<ContactDto> =
                    generateSequence { hasNext(cursor) }
                        .map {
                            ContactMapper
                                .mapToContactWithoutPhone(it)
                                .apply { phoneList = getContactNumbers(id) }
                        }.toList()

                cursor.close()
                return contacts
            } ?: emptyList()


    private fun hasNext(cursor: Cursor) = if (cursor.moveToNext()) cursor else null

    private fun getContactNumbers(id: Long): List<String> =
        cursorCreator
            .createPhoneCursor(id)
            ?.let { cursor ->
                val phones: List<String> = generateSequence { hasNext(cursor) }
                    .map { ContactMapper.mapPhone(it) }
                    .toList()

                cursor.close()

                return phones
            } ?: emptyList()

}