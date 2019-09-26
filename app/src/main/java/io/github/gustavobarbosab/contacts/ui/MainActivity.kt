package io.github.gustavobarbosab.contacts.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import io.github.gustavobarbosab.contacts.R
import io.github.gustavobarbosab.contacts.ui.contacts.list.ContactListFragment
import io.github.gustavobarbosab.contacts.utils.replace

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.replace(newFragment = ContactListFragment.newInstance())
    }
}
