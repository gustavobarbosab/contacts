package io.github.gustavobarbosab.contacts.di

import io.github.gustavobarbosab.contacts.data.repository.ContactsRepository
import io.github.gustavobarbosab.contacts.data.repository.ContactsRepositoryImpl
import io.github.gustavobarbosab.contacts.data.source.ContactsDataSource
import io.github.gustavobarbosab.contacts.data.source.local.contacts.ContactCursorCreator
import io.github.gustavobarbosab.contacts.data.source.local.contacts.ContactsLoader
import io.github.gustavobarbosab.contacts.data.source.local.contacts.ContactsLocalDataSource
import io.github.gustavobarbosab.contacts.data.source.remote.contacts.ContactsRemoteDataSource
import io.github.gustavobarbosab.contacts.ui.contacts.create.CreateContactViewModel
import io.github.gustavobarbosab.contacts.ui.contacts.list.ContactListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

class Module {
    companion object {

        val appModule = module {
            factory { ContactsLoader(get()) }
            single { ContactCursorCreator(androidContext().contentResolver) }
        }

        val viewModelModule = module {
            viewModel { ContactListViewModel(get()) }
            viewModel { CreateContactViewModel() }
        }

        val repositoryModule = module {
            single<ContactsRepository> {
                ContactsRepositoryImpl(get(named(CONTACT_LOCAL_DS)), get(named(CONTACT_REMOTE_DS)))
            }
        }

        val dataSourceModule = module {
            single<ContactsDataSource>(named(CONTACT_LOCAL_DS)) { ContactsLocalDataSource(get()) }
            single<ContactsDataSource>(named(CONTACT_REMOTE_DS)) { ContactsRemoteDataSource() }
        }

        private const val CONTACT_REMOTE_DS = "CONTACT_REMOTE_DS"
        private const val CONTACT_LOCAL_DS = "CONTACT_LOCAL_DS"
    }
}

