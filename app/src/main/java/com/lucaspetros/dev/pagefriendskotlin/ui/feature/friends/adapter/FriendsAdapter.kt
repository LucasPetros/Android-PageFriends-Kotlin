package com.lucaspetros.dev.pagefriendskotlin.ui.feature.friends.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucaspetros.dev.pagefriendskotlin.data.model.User
import com.lucaspetros.dev.pagefriendskotlin.databinding.LayoutAdapterMyListFriendsBinding
import com.lucaspetros.dev.pagefriendskotlin.ui.utils.Picasso

class FriendsAdapter(var userList: MutableList<User>) :
    RecyclerView.Adapter<FriendsAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutAdapterMyListFriendsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user: User = userList[position]
        val fullName: String = user.firstName + " " + user.lastName
        holder.binding.tvEmailUser.text = user.email
        holder.binding.tvNameUser.text = fullName
        user.avatar?.let { Picasso.picasso(holder.binding.ivAvatar, it) }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(val binding: LayoutAdapterMyListFriendsBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

}