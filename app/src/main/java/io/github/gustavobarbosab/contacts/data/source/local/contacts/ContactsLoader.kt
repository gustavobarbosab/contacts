package io.github.gustavobarbosab.contacts.data.source.local.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import io.github.gustavobarbosab.contacts.domain.ContactDto

class ContactsLoader(private val cursorCreator: ContactCursorCreator) {

    private fun getContacts(
        name: String? = null
    ): LiveData<List<ContactDto>> =
        liveData {
            val contacts = getContactsInfo(name)
            emit(contacts)
        }


    private fun getContactsInfo(name: String? = null): List<ContactDto> =
        cursorCreator
            .createCursorToContactsInfo(name)
            ?.let { cursor ->
                if (!cursor.moveToNext()) return emptyList()

                val contacts: List<ContactDto> = generateSequence { cursor }
                    .map {
                        ContactMapper
                            .mapToContactWithoutPhone(it)
                            .apply { phoneList = getContactNumbers(id) }
                    }.toList()

                cursor.close()

                contacts
            } ?: emptyList()


    private fun getContactNumbers(id: Long): List<String> =
        cursorCreator
            .createPhoneCursor(id)
            ?.let { cursor ->
                if (!cursor.moveToNext()) return emptyList()

                val phones: List<String> = generateSequence { cursor }
                    .map { ContactMapper.mapPhone(it) }
                    .toList()

                cursor.close()

                phones
            } ?: emptyList()

}