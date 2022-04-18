package com.example.gitclientapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.gitclientapp.Contract
import com.example.gitclientapp.R
import com.example.gitclientapp.domain.UserProfile

class LoginListAdapter(
    private var users: List<UserProfile>,
    private var onItemClicklistener: Contract.OnItemClickListener
) : RecyclerView.Adapter<LoginListAdapter.LoginListViewHolder>() {



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LoginListAdapter.LoginListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LoginListViewHolder(
            inflater.inflate(R.layout.login_list_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: LoginListAdapter.LoginListViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size

    }

    inner class LoginListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user:UserProfile) {
            val loginItemView = itemView.findViewById<TextView>(R.id.login_item_text_view)
            val locationItemView = itemView.findViewById<TextView>(R.id.location_item_text_view)
            loginItemView.text = user.login
            locationItemView.text = user.location
            loginItemView.setOnClickListener {
                onItemClicklistener.onItemClick()
            }

        }
    }

}