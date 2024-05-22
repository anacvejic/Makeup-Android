package com.example.makeup.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makeup.domain.model.MakeupItem
import com.example.makeup.domain.repository.MakeupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: MakeupRepository
) : ViewModel() {
    val _product = MutableLiveData<List<MakeupItem>>()
    val product: LiveData<List<MakeupItem>> get() = _product

    fun getAllProducts(brand: String, category: String) = viewModelScope.launch {
        repository.getProduct(category).let { response ->
            if (response.isSuccessful) {
                _product.postValue(response.body()
                    ?.filter { it.brand == brand && it.category == category }?.sortedBy { it.name })
            } else {
                Log.i("Error", "Error")
            }
        }
    }
}