package com.sector.contacts.ui.fragments.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.sector.contacts.databinding.FragmentEditUserBinding
import com.sector.contacts.ui.dialogs.DeleteUserDialog
import com.sector.contacts.ui.fragments.edit.viewmodel.EditUserViewModel
import com.sector.contacts.util.hideSoftKeyboard
import com.sector.contacts.util.navigateUp
import com.sector.contacts.util.setActiveField
import com.sector.contacts.util.showSoftKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditUserFragment : Fragment() {

    private var _binding: FragmentEditUserBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EditUserViewModel by viewModels()

    private val args: EditUserFragmentArgs by navArgs()

    var fields: List<Pair<TextInputEditText, MaterialTextView>> = listOf()

    private var startName = ""
    private var startSurname = ""

    private val deleteUserDialog by lazy {
        DeleteUserDialog(
            onClick = {
                deleteUser()
                navigateUp()
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            hideSoftKeyboard(true)
            navigateUp()
        }
        binding.btnDelete.setOnClickListener {
            deleteUserDialog.show(childFragmentManager, "TAG")
        }
        binding.btnSave.isEnabled = false
        binding.etName.setText(args.user.name)
        binding.etSurname.setText(args.user.surname)

        startName = args.user.name
        startSurname = args.user.surname

        listOf(
            binding.etName to binding.tvEtNameLabel,
            binding.etSurname to binding.tvEtSurnameLabel
        ).also {
            fields = it
        }.forEach { et ->
            et.first.setOnFocusChangeListener { v, hasFocus ->
                et.setActiveField(context = requireContext(), active = hasFocus)

                if (hasFocus) {
                    v.showSoftKeyboard()
                }
            }

            et.first.addTextChangedListener {
                binding.btnSave.isEnabled =
                    (binding.etName.text?.isNotEmpty() == true && binding.etSurname.text?.isNotEmpty() == true)
                            && (binding.etName.text.toString() != startName || binding.etSurname.text.toString() != startSurname)
            }
        }
        fields[0].setActiveField(context = requireContext())
        fields[1].setActiveField(context = requireContext())

        binding.btnSave.setOnClickListener {
            grabFromFields()
            navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun deleteUser() {
        viewModel.deleteUser(args.user)
    }

    private fun grabFromFields() {
        viewModel.saveUser(
            id = args.user.id,
            name = binding.etName.text.toString(),
            surname = binding.etSurname.text.toString()
        )
    }
}