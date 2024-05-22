package com.example.makeup.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.makeup.databinding.FragmentBrandBinding
import com.example.makeup.ui.epoxy.controller.BrandEpoxyController
import com.example.makeup.ui.viewmodel.BrandViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrandFragment : Fragment() {

    private var _binding: FragmentBrandBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: BrandViewModel by viewModels()
    private val controller = BrandEpoxyController(::onBrandSelected)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBrandBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.epoxyListBrands.setController(controller)

        viewmodel.brands.observe(viewLifecycleOwner, Observer { response ->
            if (response == null) {
                Toast.makeText(requireContext(), "Unsuccessful network call", Toast.LENGTH_LONG)
                    .show()
            } else {
                binding.progressbar.visibility = View.VISIBLE
                controller.setData(response)
                binding.progressbar.visibility = View.GONE
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onBrandSelected(brand: String) {
        val action = BrandFragmentDirections.actionBrandFragmentToCategoryFragment(brand)
        findNavController().navigate(action)
    }
}