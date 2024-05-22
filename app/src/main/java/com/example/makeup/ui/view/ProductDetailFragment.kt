package com.example.makeup.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.makeup.ui.MainActivity
import com.example.makeup.R
import com.example.makeup.databinding.FragmentProductDetailBinding
import com.example.makeup.domain.model.MakeupItem


class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var productdetail: MakeupItem
    private val args: ProductDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productdetail = args.productdetail!!
        (activity as MainActivity).supportActionBar?.title =
            "Category ${productdetail.name.uppercase()}"

        binding.apply {
            tvName.text = "${productdetail.brand.uppercase()} ${productdetail.name.uppercase()}"
            if (productdetail.image_link != null) {
                image.load(productdetail.image_link)
            } else {
                image.load(R.drawable.ic_noimage)
            }
            tvPrice.text = "Price $${productdetail.price}"
            tvDescription.text = productdetail.description

            tvWeblink.setOnClickListener {
                var intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(productdetail.product_link)
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}