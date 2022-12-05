package com.example.cupcakeorderappvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcakeorderappvm.databinding.FragmentNameBinding
import com.example.cupcakeorderappvm.model.OrderViewModel

class NameFragment : Fragment() {

    private var binding: FragmentNameBinding? = null

    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding = FragmentNameBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            fragmentName = this@NameFragment
        }
        setErrorNameListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setErrorNameListener() {
        binding?.textInputEditTextName?.addTextChangedListener {
            if (binding?.textInputEditTextName?.text.toString().isNotEmpty()) {
                binding?.textInputLayoutName?.error = null
            }
        }
    }

    fun goToNextScreen() {
        if (binding?.textInputEditTextName?.text.toString().isEmpty()) {
            binding?.textInputLayoutName?.error = getString(R.string.error_name)
        } else {
            sharedViewModel.setUsername(binding?.textInputEditTextName?.text.toString())
            findNavController().navigate(R.id.action_nameFragment_to_flavorFragment)
        }
    }

    fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_nameFragment_to_startFragment)
    }

}