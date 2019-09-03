package io.github.gustavobarbosab.contacts.data.source.local.contacts

import android.content.ContentResolver
import android.database.Cursor
import android.provider.ContactsContract
import java.util.*

class ContactCursorCreator(private val contentResolver: ContentResolver?) {

    fun createCursorToContactsInfo(name: String? = null): Cursor? {
        val uri = ContactsContract.Data.CONTENT_URI
        val selection = getSelection()
        val selectionArgs = getSearchList(name)
        val sortOrder = getSortOrder()
        return contentResolver
            ?.query(
                uri,
                PROJECTION,
                selection,
                selectionArgs,
                sortOrder
            )
    }

    fun createPhoneCursor(userId: Long): Cursor? {
        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val selector = "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?"
        return contentResolver
            ?.query(
                uri,
                PHONE_PROJECTION,
                selector,
                arrayOf(userId.toString()),
                null
            )
    }

    private fun getSelection(): String? =
        "lower(${ContactsContract.Data.DISPLAY_NAME_PRIMARY}) GLOB ?"

    private fun getSearchList(name: String?): Array<String>? =
        name?.let { arrayOf(getSearchText(it)) }

    private fun getSearchText(name: String): String =
        "* ${addSpecialCharactersOptions(name.toLowerCase(Locale.getDefault()))} *"

    private fun getSortOrder(): String =
        "${ContactsContract.Data.DISPLAY_NAME} COLLATE NOCASE ASC"

    private fun addSpecialCharactersOptions(searchText: String): String {
        return searchText.toLowerCase(Locale.getDefault())
            .replace("[aáàäâã]".toRegex(), "\\[aáàäâã\\]")
            .replace("[eéèëê]".toRegex(), "\\[eéèëê\\]")
            .replace("[iíìî]".toRegex(), "\\[iíìî\\]")
            .replace("[oóòöôõ]".toRegex(), "\\[oóòöôõ\\]")
            .replace("[uúùüû]".toRegex(), "\\[uúùüû\\]")
            .replace("[cç]".toRegex(), "\\[cç\\]")
            .replace("*", "[*]")
            .replace("?", "[?]")
    }

    companion object {
        private val PROJECTION = arrayOf(
            ContactsContract.Data.CONTACT_ID,
            ContactsContract.Data.DISPLAY_NAME,
            ContactsContract.Data.PHOTO_URI
        )

        private val PHONE_PROJECTION = arrayOf(
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )
    }
}