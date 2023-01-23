package com.sector.contacts.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.sector.contacts.databinding.FragmentHomeBinding
import com.sector.contacts.entity.User
import com.sector.contacts.ui.fragments.home.adapter.HomeAdapter
import com.sector.contacts.ui.fragments.home.viewmodel.HomeViewModel
import com.sector.contacts.util.navigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addNewContact.setOnClickListener {
            navigate(
                HomeFragmentDirections.onAdd()
            )
        }

        lifecycle.coroutineScope.launch {
            viewModel.getAllUsers().collect {
                createUserItems(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createUserItems(users: List<User>) {
        val rvItems = mutableListOf<Any>()
        for (user in users) {
            rvItems.add(
                user
            )
        }
        if (rvItems.isEmpty()) {
            binding.layoutNotFount.isVisible = true
        } else {
            binding.layoutNotFount.isGone = true
            setItems(rvItems)
        }
    }

    private fun setItems(rvItems: MutableList<Any>) {
        binding.rvUsers.adapter = ListDelegationAdapter(
            HomeAdapter(
                onClick = {

                }
            )
        ).apply {
            items = rvItems

            notifyItemChanged(0, rvItems.count() - 1)
        }
    }
}