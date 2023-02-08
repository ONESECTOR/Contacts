package com.sector.contacts.ui.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.sector.contacts.databinding.FragmentAddUserBinding
import com.sector.contacts.ui.fragments.add.viewmodel.AddUserViewModel
import com.sector.contacts.util.addSystemTopPadding
import com.sector.contacts.util.hideSoftKeyboard
import com.sector.contacts.util.navigateUp
import com.sector.contacts.util.setActiveField
import com.sector.contacts.util.showSoftKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserFragment : Fragment() {

    private var _binding: FragmentAddUserBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddUserViewModel by viewModels()

    private var fields: List<Pair<TextInputEditText, MaterialTextView>> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddUserBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.addSystemTopPadding()
        binding.btnBack.setOnClickListener {
            hideSoftKeyboard()
            navigateUp()
        }
        binding.btnAdd.isEnabled = false
        listOf(
            binding.etName to binding.tvEtNameLabel,
            binding.etSurname to binding.tvEtSurnameLabel
        ).also {
            fields = it
        }.forEach { et ->
            et.first.setOnFocusChangeListener { v, hasFocus ->
                et.setActiveField(requireContext(), hasFocus)

                if (hasFocus) {
                    v.showSoftKeyboard()
                }
            }

            et.first.addTextChangedListener {
                binding.btnAdd.isEnabled =
                    (binding.etName.text?.isNotEmpty() == true
                            && binding.etSurname.text?.isNotEmpty() == true)
            }
        }
        binding.btnAdd.setOnClickListener {
            grabFromFields()
            navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun grabFromFields() {
        val name = binding.etName.text.toString()
        val surname = binding.etSurname.text.toString()

        viewModel.addUser(name, surname)
    }
}