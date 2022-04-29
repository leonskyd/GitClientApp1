package com.example.gitclientapp.ui.listFragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gitclientapp.databinding.LoginListFragmentBinding
import com.example.gitclientapp.ui.UserFragment.Controller
import java.lang.IllegalStateException

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(activity !is Controller) {
            throw IllegalStateException("Activity shall extend Controller")
        }
    }
    private val controller by lazy { activity as Controller }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userList = viewModel.getUsers()
        val adapter = LoginListAdapter(userList)
        binding.mainRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val login = userList[position].login
                if (login != null) {
                    controller.openDetailScreen(login)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
