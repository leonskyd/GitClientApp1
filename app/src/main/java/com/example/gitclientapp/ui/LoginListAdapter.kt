package com.example.gitclientapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitclientapp.Contract
import com.example.gitclientapp.databinding.LoginListItemBinding
import com.example.gitclientapp.domain.UserProfile

class LoginListAdapter(
    private var users: List<UserProfile>
) : RecyclerView.Adapter<LoginListAdapter.LoginListViewHolder>() {
    private lateinit var _listener: Contract.OnItemClickListener

    fun setOnItemClickListener(listener: Contract.OnItemClickListener) {
        _listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LoginListAdapter.LoginListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LoginListViewHolder(
            LoginListItemBinding.inflate(inflater), _listener
        )
    }

    override fun onBindViewHolder(holder: LoginListAdapter.LoginListViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size

    }

    inner class LoginListViewHolder(
        private val binding: LoginListItemBinding,
        listener: Contract.OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.loginItemTextView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
        fun bind(user: UserProfile) {
            binding.loginItemTextView.text = user.login
            binding.locationItemTextView.text = user.location
        }
    }
}