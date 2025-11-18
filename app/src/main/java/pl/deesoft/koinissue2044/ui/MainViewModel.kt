package pl.deesoft.koinissue2044.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.deesoft.koinissue2044.domain.GetGreetingUseCase

class MainViewModel(
    private val getGreetingUseCase: GetGreetingUseCase
) : ViewModel() {

    private val _greetingText = MutableStateFlow("Click the button!")
    val greetingText: StateFlow<String> = _greetingText.asStateFlow()

    fun updateGreeting(name: String) {
        viewModelScope.launch {
            _greetingText.value = getGreetingUseCase.execute(name)
        }
    }
}
