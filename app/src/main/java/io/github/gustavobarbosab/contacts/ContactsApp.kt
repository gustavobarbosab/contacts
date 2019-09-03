package io.github.gustavobarbosab.contacts

import android.app.Application
import io.github.gustavobarbosab.contacts.di.appModule
import io.github.gustavobarbosab.contacts.di.viewModelModule
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
                    viewModelModule
                )
            )
        }
    }
}
