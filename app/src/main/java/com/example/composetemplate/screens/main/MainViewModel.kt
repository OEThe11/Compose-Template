package com.example.composetemplate.screens.main

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetemplate.data.Resource
import com.example.composetemplate.database.StandardEntity
import com.example.composetemplate.repository.StandardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: StandardRepository
): ViewModel() {
    private val _standardStatus = MutableStateFlow<Resource<List<StandardEntity>>>(Resource.Loading())
    val standardStatus: StateFlow<Resource<List<StandardEntity>>> = _standardStatus

    fun getStandardList() {
        viewModelScope.launch(Dispatchers.IO) {
            _standardStatus.value = repository.standardInfo()
        }
    }
}