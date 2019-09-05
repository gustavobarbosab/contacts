package io.github.gustavobarbosab.contacts.ui.contacts.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.gustavobarbosab.contacts.R
import io.github.gustavobarbosab.contacts.data.repository.ContactsRepository
import io.github.gustavobarbosab.contacts.domain.ContactDto
import io.github.gustavobarbosab.contacts.utils.LiveDataEvent
import io.github.gustavobarbosab.contacts.utils.Result.Success
import kotlinx.coroutines.launch

class ContactListViewModel(private val contactsRepository: ContactsRepository) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _snackBarText = MutableLiveData<LiveDataEvent<Int>>()
    val snackBarText: LiveData<LiveDataEvent<Int>> = _snackBarText

    private val _loadContacts = MutableLiveData<List<ContactDto>>()
    val loadContacts: LiveData<List<ContactDto>> = _loadContacts

    fun getContactList(force: Boolean) {
        _dataLoading.value = true
        viewModelScope.launch {
            when (val response = contactsRepository.getContacts(force)) {
                is Success -> _loadContacts.value = response.data
                else -> _snackBarText.value = LiveDataEvent(R.string.app_name)
            }
            _dataLoading.value = false
        }
    }
}