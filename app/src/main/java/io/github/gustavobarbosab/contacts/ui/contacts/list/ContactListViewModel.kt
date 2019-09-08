package io.github.gustavobarbosab.contacts.ui.contacts.list

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.gustavobarbosab.contacts.R
import io.github.gustavobarbosab.contacts.data.repository.ContactsRepository
import io.github.gustavobarbosab.contacts.domain.ContactDto
import io.github.gustavobarbosab.contacts.utils.Event
import io.github.gustavobarbosab.contacts.utils.Result.Success
import kotlinx.coroutines.launch

class ContactListViewModel(private val contactsRepository: ContactsRepository) : ViewModel() {

    private val _snackBarTextError = MutableLiveData<Event<Int>>()
    val snackBarTextError: LiveData<Event<Int>> = _snackBarTextError

    private val _loadContacts = MutableLiveData<List<ContactDto>>()
    val loadContacts: LiveData<List<ContactDto>> = _loadContacts

    val isRefreshing = ObservableBoolean()

    fun getContactList(force: Boolean) {
        isRefreshing.set(true)
        viewModelScope.launch {
            when (val response = contactsRepository.getContacts(force)) {
                is Success -> _loadContacts.value = response.data
                else -> _snackBarTextError.value = Event(R.string.error_load_contacts)
            }
            isRefreshing.set(false)
        }
    }

    fun onClickAddContact() {
        _snackBarTextError.value = Event(R.string.create_contacts)
    }
}