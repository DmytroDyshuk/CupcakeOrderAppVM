/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cupcakeorderappvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.cupcakeorderappvm.databinding.FragmentSummaryBinding
import com.example.cupcakeorderappvm.model.OrderViewModel

class SummaryFragment : Fragment() {

    private var binding: FragmentSummaryBinding? = null

    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val fragmentBinding = FragmentSummaryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            fragmentSummary = this@SummaryFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun sendOrder() {
        Toast.makeText(activity, "Send Order", Toast.LENGTH_SHORT).show()
    }

}