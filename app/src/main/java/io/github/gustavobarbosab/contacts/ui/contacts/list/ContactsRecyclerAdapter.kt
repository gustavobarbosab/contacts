package io.github.gustavobarbosab.contacts.ui.contacts.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.gustavobarbosab.contacts.R
import io.github.gustavobarbosab.contacts.databinding.ItemContactsBinding
import io.github.gustavobarbosab.contacts.domain.ContactDto

class ContactsRecyclerAdapter : RecyclerView.Adapter<ContactsRecyclerAdapter.ContactsViewHolder>() {

    var contactsList: MutableList<ContactDto> = mutableListOf()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    fun clearList() {
        contactsList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val binding: ItemContactsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_contacts,
            parent,
            false
        )
        return ContactsViewHolder(binding)
    }

    override fun getItemCount(): Int = contactsList.size

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.binding.model = contactsList[position]
    }

    class ContactsViewHolder(val binding: ItemContactsBinding) :
        RecyclerView.ViewHolder(binding.root)
}