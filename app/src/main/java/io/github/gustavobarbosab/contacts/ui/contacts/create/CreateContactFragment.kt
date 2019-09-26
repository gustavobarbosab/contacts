package io.github.gustavobarbosab.contacts.ui.contacts.create

import android.os.Bundle
import io.github.gustavobarbosab.contacts.R
import io.github.gustavobarbosab.contacts.databinding.FragmentCreateContactBinding
import io.github.gustavobarbosab.contacts.ui.BaseFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class CreateContactFragment : BaseFragmentBinding<FragmentCreateContactBinding>() {

    override val layoutResourceId: Int = R.layout.fragment_create_contact

    override fun onCreateView(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
    }

    val viewModel: CreateContactViewModel by viewModel()
}