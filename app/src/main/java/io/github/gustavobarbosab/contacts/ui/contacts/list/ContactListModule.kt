package io.github.gustavobarbosab.contacts.ui.contacts.list

import org.koin.dsl.module

class ContactListModule {
    companion object {
        val moduleInstance = module {
            factory { ContactsRecyclerAdapter() }
        }
    }
}