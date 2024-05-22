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
class CategoryViewModel @Inject constructor(
    private val repository: MakeupRepository
) : ViewModel() {
    val _category = MutableLiveData<List<MakeupItem>>()
    val category: LiveData<List<MakeupItem>> get() = _category

    fun getAllCategory(brand: String) = viewModelScope.launch {
        repository.getCategory(brand).let { response ->
            if (response.isSuccessful) {
                _category.postValue(response.body()
                    ?.filter { it.category != null && it.category != "" }
                    ?.distinctBy { it.category }?.sortedBy { it.category })
            } else {
                Log.i("Error", "Error")
            }
        }
    }
}