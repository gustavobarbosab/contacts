package io.github.gustavobarbosab.contacts.ui.contacts.list

import android.Manifest.permission
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import io.github.gustavobarbosab.contacts.R
import io.github.gustavobarbosab.contacts.databinding.FragmentContactListBinding
import io.github.gustavobarbosab.contacts.ui.BaseFragmentBinding
import io.github.gustavobarbosab.contacts.utils.isPermissionDenied
import io.github.gustavobarbosab.contacts.utils.requestPermission
import kotlinx.android.synthetic.main.content_contact_list.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ContactListFragment : BaseFragmentBinding<FragmentContactListBinding>() {

    override val layoutResourceId: Int = R.layout.fragment_contact_list
    private val viewModel: ContactListViewModel by viewModel()
    private val adapter: ContactsRecyclerAdapter by inject()

    override fun onCreateView(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeLoadContacts()
        observeMessage()
        observeRequestPermissionReadContact()
        requestPermission()
    }

    private fun observeRequestPermissionReadContact() {
        viewModel.requestPermissionReadContact.observe(this, Observer {
            requestPermission(permission.READ_CONTACTS)
        })
    }

    private fun requestPermission() {
        viewModel.checkReadContactPermission(context?.isPermissionDenied(permission.READ_CONTACTS))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        viewModel.checkPermissionResult(requestCode, grantResults)
    }

    private fun observeLoadContacts() {
        viewModel
            .loadContacts
            .observe(this, Observer {
                it?.let { list -> adapter.contactsList = list.toMutableList() }
            })
    }

    private fun observeMessage() {
        viewModel.snackBarTextMessage.observe(this, Observer {
            it.getContentIfNotHandled()?.let { textResource ->
                Snackbar.make(binding.root, textResource, Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView() {
        rvContactList.adapter = adapter
    }

    companion object {
        fun newInstance(): Fragment = ContactListFragment()
    }
}