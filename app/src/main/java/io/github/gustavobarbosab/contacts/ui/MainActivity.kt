package io.github.gustavobarbosab.contacts.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar
import io.github.gustavobarbosab.contacts.R
import io.github.gustavobarbosab.contacts.ui.contacts.list.ContactListFragment
import io.github.gustavobarbosab.contacts.utils.replace
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setupFabClick()
        supportFragmentManager?.replace(newFragment = ContactListFragment.newInstance())
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar.apply {
            title = "Teste Gustavo"
        })
    }

    private fun setupFabClick() {
        fab.setOnClickListener { view ->
            Snackbar
                .make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }

}
