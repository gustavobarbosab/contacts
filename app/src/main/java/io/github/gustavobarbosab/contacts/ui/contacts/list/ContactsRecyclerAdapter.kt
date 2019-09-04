package io.github.gustavobarbosab.contacts.ui.contacts.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.gustavobarbosab.contacts.R
import io.github.gustavobarbosab.contacts.domain.ContactDto
import kotlinx.android.synthetic.main.item_contacts.view.*

class ContactsRecyclerAdapter : RecyclerView.Adapter<ContactsRecyclerAdapter.ContactsViewHolder>() {

    var contactsList: MutableList<ContactDto> = mutableListOf()
        set(value) {
            contactsList.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder =
        ContactsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_contacts,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = contactsList.size

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.itemView.tvContactListName.text = contactsList[position].name
    }

    class ContactsViewHolder(view: View) : RecyclerView.ViewHolder(view)
}