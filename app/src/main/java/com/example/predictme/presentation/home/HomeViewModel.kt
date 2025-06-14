package com.example.predictme.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.predictme.data.repository.DataRepository
import com.example.predictme.model.CombinedData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: CombinedData) : UiState()
    data class Error(val message: String) : UiState()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        fetchData(name = "")
    }


    fun fetchData(name: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val result = dataRepository.getCombinedData(name)
            if (result.isSuccess) {
                _uiState.value = UiState.Success(result.getOrThrow())
            } else {
                _uiState.value = UiState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }
}