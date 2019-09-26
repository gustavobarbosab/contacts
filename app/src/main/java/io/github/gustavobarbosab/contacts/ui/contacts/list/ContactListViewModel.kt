package io.github.gustavobarbosab.contacts.ui.contacts.list

import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.gustavobarbosab.contacts.R
import io.github.gustavobarbosab.contacts.data.repository.ContactsRepository
import io.github.gustavobarbosab.contacts.domain.ContactDto
import io.github.gustavobarbosab.contacts.utils.Event
import io.github.gustavobarbosab.contacts.utils.REQUEST_CODE_PERMISSION_READ_CONTACT
import io.github.gustavobarbosab.contacts.utils.Result.Success
import kotlinx.coroutines.launch

class ContactListViewModel(private val contactsRepository: ContactsRepository) : ViewModel() {

    private val _snackBarTextMessage = MutableLiveData<Event<Int>>()
    val snackBarTextMessage: LiveData<Event<Int>> = _snackBarTextMessage

    private val _loadContacts = MutableLiveData<List<ContactDto>>()
    val loadContacts: LiveData<List<ContactDto>> = _loadContacts

    private val _requestPermissionReadContact = MutableLiveData<Unit>()
    val requestPermissionReadContact: LiveData<Unit> = _requestPermissionReadContact

    val isRefreshing = ObservableBoolean()

    fun getContactList(force: Boolean) {
        isRefreshing.set(true)
        viewModelScope.launch {
            when (val response = contactsRepository.getContacts(force)) {
                is Success -> _loadContacts.value = response.data
                else -> _snackBarTextMessage.value = Event(R.string.error_load_contacts)
            }
            isRefreshing.set(false)
        }
    }

    fun onClickAddContact() {
        _snackBarTextMessage.value = Event(R.string.create_contacts)
    }

    fun requestContactList(permissionGranted: Boolean?) {
        when (permissionGranted) {
            true -> getContactList(false)
            else -> _requestPermissionReadContact.value = Unit
        }
    }

    fun checkPermissionResult(requestCode: Int, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSION_READ_CONTACT) {
            when (grantResults.firstOrNull()) {
                PERMISSION_GRANTED -> getContactList(false)
                else -> _snackBarTextMessage.value =
                    Event(R.string.contact_list_fragment_permission_denied_message)
            }
        }
    }
}