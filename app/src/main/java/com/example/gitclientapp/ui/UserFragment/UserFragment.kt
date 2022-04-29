package com.example.gitclientapp.ui.UserFragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.gitclientapp.databinding.FragmentUserBinding
import com.example.gitclientapp.domain.GitRepoEntity
import com.example.gitclientapp.domain.UserProfile
import io.reactivex.rxjava3.core.Observable
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : Fragment(){

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserViewModel by viewModel()

    companion object {
        private const val KEY_STRING = "KEY_STRING"
        fun newInstance(login: String) = UserFragment().apply {
            arguments = Bundle()
            arguments?.putString(KEY_STRING, login)
        }
    }

    private val controller by lazy { activity as Controller }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(
            inflater, container, false
        )
        val inputData = this.arguments?.get("KEY_STRING")
        binding.loginTextView.text = inputData.toString()
        initBackPressed()
        return binding.root

        viewModel.getLiveDataObserver()
        viewModel.getdetailsLiveDataObserver()
    }

    private fun initBackPressed() {
        val callback = object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                controller.backToList()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }


    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = binding.loginTextView.text.toString()
        viewModel.let {
            it.makeCall(username)
            it.detailsLiveDataList.observe(this, { details ->
                showDetails(details)
            })
            it.liveDataList.observe(this, Observer { reposList ->
                showReposText(reposList)
            })
        }
    }

    private fun showDetails(details: UserProfile) {
        val userName = details.name?: "Uknown"
        val userLocation = details.location?: "Uknown"
        val userAvatar = details.avatar_url
        Observable.just(userName, userLocation,userAvatar)
            .map{
                Glide.with(this)
                    .load(userAvatar)
                    .into(binding.avatarImageView)
            }
            .subscribe{binding.nameTextView.text = userName
                    binding.locationTextView.text = userLocation}
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
interface Controller {
    fun openDetailScreen(login: String)
    fun backToList()
}