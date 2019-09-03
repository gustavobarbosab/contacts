package io.github.gustavobarbosab.contacts.ui.contacts.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.gustavobarbosab.contacts.R
import org.koin.android.viewmodel.ext.android.viewModel

class ContactListFragment: Fragment() {

    val viewModel: ContactListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contact_list,container,false)
    }

    companion object {
        fun newInstance() = ContactListFragment()
    }
}