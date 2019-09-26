package io.github.gustavobarbosab.contacts.ui.contacts.list

import android.net.Uri
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
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    fun clearList() {
        contactsList.clear()
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
        val contact = contactsList[position]
        holder.itemView.tvContactListName.text = contactsList[position].name
        holder.itemView.tvContactListNumber.text = contactsList[position].phoneList[0]
        if (contact.imageUrl?.isNotEmpty() == true) {
            val uri = Uri.parse(contact.imageUrl)
            holder.itemView.ivContactListPhonto.setImageURI(uri)
        } else {
            holder.itemView.ivContactListPhonto.setImageResource(R.drawable.ic_person)
        }
    }

    class ContactsViewHolder(view: View) : RecyclerView.ViewHolder(view)
}