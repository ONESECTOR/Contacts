package com.sector.contacts.ui.fragments.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.sector.contacts.databinding.ItemUserBinding
import com.sector.contacts.entity.User
import com.sector.contacts.ui.custom.BaseViewHolder
import com.sector.contacts.util.binding

class HomeAdapter(
    private val onClick: (user: User) -> Unit
): AdapterDelegate<MutableList<Any>>() {

    override fun isForViewType(items: MutableList<Any>, position: Int): Boolean =
        items[position] is User

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(parent)

    override fun onBindViewHolder(
        items: MutableList<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        holder as ViewHolder
        holder.bind(item = items[position] as User, position = position, count = items.count())
    }

    inner class ViewHolder(
        parent: ViewGroup
    ) : BaseViewHolder<ItemUserBinding>(
        parent.binding(ItemUserBinding::inflate)
    ) {
        fun bind(item: User, position: Int, count: Int) {
            binding.tvFullName.text = "${item.name} ${item.surname}"

            itemView.setOnClickListener {
                onClick.invoke(
                    User(id = item.id, name = item.name, surname = item.surname)
                )
            }
        }
    }
}