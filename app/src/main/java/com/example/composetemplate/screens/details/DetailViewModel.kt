package com.example.composetemplate.screens.details

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
class DetailViewModel @Inject constructor(
    private val repository: StandardRepository
): ViewModel(){

    private val _detail = MutableStateFlow<Resource<StandardEntity>>(Resource.Loading())
    val detail: StateFlow<Resource<StandardEntity>> = _detail

    //This is the setup with Flow as the return type and with the use of Room DB
    fun getDetail(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getById(id).collect{resource->
                _detail.value = resource
            }
        }
    }

}