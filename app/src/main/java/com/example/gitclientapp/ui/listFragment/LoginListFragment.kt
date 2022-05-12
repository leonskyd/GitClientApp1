package com.example.gitclientapp.ui.listFragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.gitclientapp.App
import com.example.gitclientapp.data.RetrofitRepositoryInterface
import com.example.gitclientapp.databinding.LoginListFragmentBinding
import com.example.gitclientapp.domain.UserProfile
import com.example.gitclientapp.ui.UserFragment.Controller
import com.example.gitclientapp.ui.UserFragment.ViewModelFactory
import java.lang.IllegalStateException
import javax.inject.Inject


class LoginListFragment : Fragment() {

    companion object {
        fun newInstance() = LoginListFragment().apply{
            App.instance.appDependenciesComponent.inject(this)
        } }


    private var _binding: LoginListFragmentBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var webRepository: RetrofitRepositoryInterface
    private val viewModel: LoginListViewModel by lazy {
        val factory = ViewModelFactory(webRepository)
        ViewModelProvider(this, factory).get(LoginListViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginListFragmentBinding.inflate(
            inflater, container, false
        )
        return binding.root
        viewModel.getUsersLiveDataObserver()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(activity !is Controller) {
            throw IllegalStateException("Activity shall extend Controller")
        }
    }
    private val controller by lazy { activity as Controller }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(savedInstanceState==null) {
            val userList = viewModel.getUsers()
            initAdapter(userList)
        } else {
            val sinceNumber = savedInstanceState.getInt("sinceNumber",10)
            viewModel.getUsersListFromApi(sinceNumber)
            viewModel.usersListLiveData.observe(this,{ webUserList ->
                initAdapter(webUserList)
            })
        }
    }



    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sinceButton.setOnClickListener {
            val sinceNumber = binding.sinceEditText.text.toString().toInt()
            viewModel.getUsersListFromApi(sinceNumber)
            viewModel.usersListLiveData.observe(this,{ webUserList ->
                initAdapter(webUserList)
            })
        }
    }

    private fun initAdapter(userList: List<UserProfile>) {
        val adapter = LoginListAdapter(userList)
        binding.mainRecyclerView.adapter = adapter
        initAdapterItemClick(adapter,userList)
    }

    private fun initAdapterItemClick(
        adapter: LoginListAdapter, userList: List<UserProfile>) {
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val login = userList[position].login
                if (login != null) {
                    controller.openDetailScreen(login)
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val sinceNumber = binding.sinceEditText.text.toString().toInt()
        outState.putInt("sinceNumber", sinceNumber)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
