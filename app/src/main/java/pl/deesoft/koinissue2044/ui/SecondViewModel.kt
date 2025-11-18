package pl.deesoft.koinissue2044.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.deesoft.koinissue2044.domain.GetGreetingUseCase

class SecondViewModel(
    private val getGreetingUseCase: GetGreetingUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _greetingText = savedStateHandle.getMutableStateFlow("field", "")
    val greetingText: StateFlow<String> = _greetingText.asStateFlow()

    private val _sharedCallCount = MutableStateFlow(0)
    val sharedCallCount: StateFlow<Int> = _sharedCallCount.asStateFlow()

    fun updateGreeting(name: String) {
        viewModelScope.launch {
            _greetingText.value = getGreetingUseCase.execute(name)
            _sharedCallCount.value = getGreetingUseCase.getCallCount()
        }
    }

    fun refreshCallCount() {
        viewModelScope.launch {
            _sharedCallCount.value = getGreetingUseCase.getCallCount()
        }
    }
}
