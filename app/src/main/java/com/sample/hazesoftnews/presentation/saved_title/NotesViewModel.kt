package com.sample.hazesoftnews.presentation.saved_title

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.hazesoftnews.domain.model.Title
import com.sample.hazesoftnews.domain.use_case.TitleUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TitlesViewModel @Inject constructor(
    private val titleUseCases: TitleUseCases
) : ViewModel() {

    private val _state = mutableStateOf<List<Title>>(emptyList())
    val state: State<List<Title>> = _state

    private var recentlyDeletedTitle: Title? = null

    private var getTitlesJob: Job? = null

    init {
        getTitles()
    }
    
    fun onEvent(event: TitlesEvent){
        when(event){

            is TitlesEvent.DeleteTitle ->{
                viewModelScope.launch {
                    titleUseCases.deleteTitle(event.title)
                    recentlyDeletedTitle = event.title
                }
            }
            is TitlesEvent.RestoreTitle ->{

                viewModelScope.launch {
                    titleUseCases.addTitle(recentlyDeletedTitle ?: return@launch)
                    recentlyDeletedTitle = null

                }

            }
        }
    }

    private fun getTitles() {
        getTitlesJob?.cancel()
        getTitlesJob = titleUseCases.getTitles().onEach { titles ->
            _state.value = titles
        }.launchIn(viewModelScope)

    }
}