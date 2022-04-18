package com.example.gitclientapp.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gitclientapp.Contract
import com.example.gitclientapp.R
import com.example.gitclientapp.databinding.LoginListFragmentBinding

class LoginListFragment : Fragment() {

    companion object {
        fun newInstance() = LoginListFragment()
    }

    private var _binding: LoginListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginListViewModel by lazy {
        ViewModelProvider(this).get(LoginListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginListFragmentBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val userList = viewModel.getUsers()
        val adapter = LoginListAdapter(userList, object: Contract.OnItemClickListener {
            override fun onItemClick() {
                openUserProfile()
            }
        })
        binding.mainRecyclerView.adapter = adapter

    }

    private fun openUserProfile() {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.list_fragment_container, UserFragment())
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}