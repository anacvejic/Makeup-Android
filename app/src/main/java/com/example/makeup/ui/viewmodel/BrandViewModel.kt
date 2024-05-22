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
class BrandViewModel @Inject constructor(
    private val repository: MakeupRepository
) : ViewModel() {
    val _brands = MutableLiveData<List<MakeupItem>>()
    val brands: LiveData<List<MakeupItem>>
        get() = _brands

    init {
        getAllBrands()
    }

    private fun getAllBrands() = viewModelScope.launch {
        repository.getBrands().let { response ->
            if (response.isSuccessful) {
                _brands.postValue(response.body()
                    ?.filter { it.brand != null && it.category != null }?.distinctBy { it.brand }
                    ?.sortedBy { it.brand })
            } else {
                Log.i("Error", "Error")
            }
        }
    }
}

