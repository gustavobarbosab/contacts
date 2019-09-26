package io.github.gustavobarbosab.contacts

import android.app.Application
import io.github.gustavobarbosab.contacts.di.Module.Companion.appModule
import io.github.gustavobarbosab.contacts.di.Module.Companion.dataSourceModule
import io.github.gustavobarbosab.contacts.di.Module.Companion.repositoryModule
import io.github.gustavobarbosab.contacts.di.Module.Companion.viewModelModule
import io.github.gustavobarbosab.contacts.ui.contacts.list.ContactListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ContactsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startDI()
    }

    private fun startDI() {
        startKoin {
            androidContext(this@ContactsApp)
            modules(
                listOf(
                    appModule,
                    viewModelModule,
                    repositoryModule,
                    dataSourceModule,
                    ContactListModule.moduleInstance
                )
            )
        }
    }
}
