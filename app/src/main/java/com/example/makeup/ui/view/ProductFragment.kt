package com.example.makeup.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.makeup.ui.MainActivity
import com.example.makeup.databinding.FragmentProductBinding
import com.example.makeup.ui.epoxy.controller.ProductEpoxyController
import com.example.makeup.domain.model.MakeupItem
import com.example.makeup.ui.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private val args: ProductFragmentArgs by navArgs()
    private lateinit var category: String
    private lateinit var brand: String
    private val viewmodel: ProductViewModel by viewModels()
    private val controller = ProductEpoxyController(::onProductSelected)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        category = args.product
        brand = args.brand

        binding.epoxyProduct.setController(controller)

        (activity as MainActivity).supportActionBar?.title = "Category ${category.uppercase()}"

        viewmodel.product.observe(viewLifecycleOwner) { response ->
            if (response == null) {
                Toast.makeText(requireContext(), "Unsuccessful network call", Toast.LENGTH_LONG)
                    .show()
                return@observe
            } else {
                binding.progressbar.visibility = View.VISIBLE
                controller.setData(response)
                binding.progressbar.visibility = View.GONE
            }
        }

        viewmodel.getAllProducts(brand, category)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onProductSelected(product: MakeupItem) {
        val action = ProductFragmentDirections.actionProductFragmentToProductDetailFragment(product)
        findNavController().navigate(action)
    }
}