package io.github.gustavobarbosab.contacts.di

import io.github.gustavobarbosab.contacts.ui.contacts.create.CreateContactViewModel
import io.github.gustavobarbosab.contacts.ui.contacts.list.ContactListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

}

val viewModelModule = module {
    viewModel { ContactListViewModel() }
    viewModel { CreateContactViewModel() }
}


