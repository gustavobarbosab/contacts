package io.github.gustavobarbosab.contacts.ui.contacts.list

import androidx.lifecycle.ViewModel
import io.github.gustavobarbosab.contacts.data.repository.ContactsRepository

class ContactListViewModel(contactsRepository: ContactsRepository): ViewModel() {

    val contactList = contactsRepository.getContacts()
}