package com.example.makeup.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.makeup.ui.MainActivity
import com.example.makeup.databinding.FragmentCategoryBinding
import com.example.makeup.ui.epoxy.controller.CategoryEpoxyController
import com.example.makeup.domain.model.MakeupItem
import com.example.makeup.ui.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: CategoryViewModel by viewModels()
    private val args: CategoryFragmentArgs by navArgs()
    private lateinit var brand: String
    private val controller = CategoryEpoxyController(::onCategorySelected)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        brand = args.category
        binding.epoxyListBrands.setController(controller)

        (activity as MainActivity).supportActionBar?.title = "Category ${brand.uppercase()}"
        Log.i("DOBIJENI PARAMETAR", "${brand}")

        viewmodel.category.observe(viewLifecycleOwner) { response ->
            response.forEach {
                it
                Log.i("BRENDOVI", "${it.category}")
            }
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
        viewmodel.getAllCategory(brand)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onCategorySelected(makeupItem: MakeupItem) {
        val action = CategoryFragmentDirections.actionCategoryFragmentToProductFragment(
            makeupItem.category,
            makeupItem.brand
        )
        findNavController().navigate(action)
    }
}