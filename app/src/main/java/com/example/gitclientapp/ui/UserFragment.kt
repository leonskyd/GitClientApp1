package com.example.gitclientapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.gitclientapp.R
import com.example.gitclientapp.databinding.FragmentUserBinding
import com.example.gitclientapp.domain.GitRepoEntity
import com.example.gitclientapp.domain.UserProfile
import kotlinx.coroutines.NonDisposableHandle.parent


class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!


    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(
            inflater, container, false
        )
        val args = this.arguments
        val inputData = args?.get("KEY_STRING")
        binding.loginTextView.text = inputData.toString()
        return binding.root
        viewModel.getLiveDataObserver()
        viewModel.getdetailsLiveDataObserver()

    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, LoginListFragment())
            transaction?.commit()
        }

        val username = binding.loginTextView.text.toString()
        viewModel.makeCall(username)
        viewModel.detailsLiveDataList.observe(this,{ details ->
           showDetails(details)
        })
        viewModel.liveDataList.observe(this, Observer { reposList ->
           showReposText(reposList)
        })

    }

    private fun showDetails(details: UserProfile) {
        binding.nameTextView.text = details.name
        binding.locationTextView.text = details.location
        Glide
            .with(this)
            .load(details.avatar_url)
            .into(binding.avatarImageView)
    }

    private fun showReposText(reposList: List<GitRepoEntity>) {
        val stringbuilder = StringBuilder()
        for (item in reposList) {
            stringbuilder.append(item.name)
            stringbuilder.append("\n")
        }
        binding.repoTextView.text = stringbuilder
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}