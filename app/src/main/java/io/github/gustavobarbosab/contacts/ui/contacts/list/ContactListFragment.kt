package io.github.gustavobarbosab.contacts.ui.contacts.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import io.github.gustavobarbosab.contacts.R
import kotlinx.android.synthetic.main.content_contact_list.*
import kotlinx.android.synthetic.main.fragment_contact_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class ContactListFragment : Fragment() {

    private val viewModel: ContactListViewModel by viewModel()
    private val adapter = ContactsRecyclerAdapter() // Injetar depois

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_contact_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFabClick()
        setupRecyclerView()
        observeLoadContacts()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getContactList(false)
    }

    private fun observeLoadContacts() {
        viewModel
            .loadContacts
            .observe(this, Observer {
                it?.let { list -> adapter.contactsList = list.toMutableList() }
            })
    }

    private fun setupRecyclerView() {
        rvContactList.adapter = adapter
    }

    private fun setupFabClick() {
        fab.setOnClickListener { view ->
            Snackbar
                .make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }
    }

    companion object {
        fun newInstance() = ContactListFragment()
    }
}