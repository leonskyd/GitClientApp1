package com.example.gitclientapp.ui.listFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitclientapp.databinding.LoginListItemBinding
import com.example.gitclientapp.domain.UserProfile

class LoginListAdapter(
    private var users: List<UserProfile>
) : RecyclerView.Adapter<LoginListAdapter.LoginListViewHolder>() {
    private lateinit var _listener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        _listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LoginListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LoginListViewHolder(
            LoginListItemBinding.inflate(inflater), _listener
        )
    }

    override fun onBindViewHolder(holder: LoginListViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size

    }

    inner class LoginListViewHolder(
        private val binding: LoginListItemBinding,
        listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.loginItemTextView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
        fun bind(user: UserProfile) {
            binding.loginItemTextView.text = user.login
        }
    }
}
interface OnItemClickListener {
    fun onItemClick(position: Int)
}