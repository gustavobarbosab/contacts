package io.github.gustavobarbosab.contacts.data.source.local.contacts

import android.database.Cursor
import android.provider.ContactsContract
import io.github.gustavobarbosab.contacts.domain.ContactDto

class ContactMapper {
    companion object {

        fun mapToContactWithoutPhone(contactInfoCursor: Cursor): ContactDto {
            val id =
                contactInfoCursor.getLong(contactInfoCursor.getColumnIndex(ContactsContract.Data.CONTACT_ID))
            val imageURI =
                contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.Data.PHOTO_URI))
                    ?: ""
            val name =
                contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME_PRIMARY))
            return ContactDto(id, imageURI, name)
        }

        fun mapPhone(
            contactPhoneCursor: Cursor
        ): String {
            val phoneIndex =
                contactPhoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            return contactPhoneCursor.getString(phoneIndex)
        }

    }
}