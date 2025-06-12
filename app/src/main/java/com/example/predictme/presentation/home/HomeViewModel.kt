package com.example.predictme.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.predictme.data.network.NetworkService
import com.example.predictme.model.PersonAge
import com.example.predictme.model.PersonGender
import com.example.predictme.model.PersonNationality
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val networkService: NetworkService
) : ViewModel() {
    private val _uiPersonAge = MutableStateFlow<PersonAge?>(null)
    val uiPersonAge: MutableStateFlow<PersonAge?> = _uiPersonAge

    private val _uiPersonGender = MutableStateFlow<PersonGender?>(null)
    val uiPersonGender: MutableStateFlow<PersonGender?> = _uiPersonGender

    private val _uiPersonNationality = MutableStateFlow<PersonNationality?>(null)
    val uiPersonNationality: MutableStateFlow<PersonNationality?> = _uiPersonNationality


    fun getUserDetails(name: String) {
        viewModelScope.launch {
            _uiPersonAge.value = networkService.getAge(name)
            _uiPersonGender.value = networkService.getGender(name)
            _uiPersonNationality.value = networkService.getNationality(name)
        }
    }
}