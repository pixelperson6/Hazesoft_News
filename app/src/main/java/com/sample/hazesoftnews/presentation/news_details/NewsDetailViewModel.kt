package com.sample.hazesoftnews.presentation.news_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.hazesoftnews.common.Constants.PARAM_INDEX
import com.sample.hazesoftnews.data.remote.dto.Article
import com.sample.hazesoftnews.data.remote.dto.Source
import com.sample.hazesoftnews.domain.model.InvalidTitleException
import com.sample.hazesoftnews.domain.model.Title
import com.sample.hazesoftnews.domain.use_case.TitleUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val titleUseCases: TitleUseCases
) : ViewModel() {

    private var currentIndex = 0

/*    private var author = ""
    private var content = ""
    private var desc = ""
    private var published = ""
    private var id = ""
    private var name = ""
    private var title = ""
    private var url = ""
    private var urlToImage = ""

    private val _article = mutableStateOf<Article>(
        Article(
            author,
            content,
            desc,
            published,
            Source(id, name),
            title,
            url,
            urlToImage
        )
    )
    val article: State<Article> = _article*/

    private val _index = mutableStateOf<Int>(0)
    val index: State<Int> = _index

    private fun currentIndex(index: Int) {
        _index.value = index
    }



    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {

        savedStateHandle.get<Int>(PARAM_INDEX)?.let { currentIndex ->
            this.currentIndex = currentIndex
        }
        currentIndex(currentIndex)
    }


    fun saveTitle(title:String) {
        viewModelScope.launch {
            try {
                titleUseCases.addTitle(
                    Title(
                        title =title,
                    )
                )
                _eventFlow.emit(UiEvent.SaveTitle)
            } catch (e: InvalidTitleException) {
                _eventFlow.emit(
                    UiEvent.ShowSnackBar(
                        message = e.message ?: "Couldn't save title"
                    )
                )
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SaveTitle : UiEvent()
    }

}