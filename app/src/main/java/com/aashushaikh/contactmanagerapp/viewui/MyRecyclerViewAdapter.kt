package com.aashushaikh.contactmanagerapp.viewui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aashushaikh.contactmanagerapp.R
import com.aashushaikh.contactmanagerapp.databinding.CardItemBinding
import com.aashushaikh.contactmanagerapp.room.User

class MyRecyclerViewAdapter(private val usersList: List<User>,
                            private val clickListener: (User)-> Unit): RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewMolder>() {

    class MyViewMolder(private val binding: CardItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User, clickListener: (User) -> Unit){
            binding.nameTV.text = user.name
            binding.emailTV.text = user.email

            binding.listItemLayout.setOnClickListener{
                clickListener(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewMolder {
        val binding: CardItemBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.card_item, parent, false)
        return MyViewMolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewMolder, position: Int) {
        holder.bind(usersList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }
}