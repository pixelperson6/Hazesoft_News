package com.sample.hazesoftnews.presentation.news_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.hazesoftnews.common.Resource
import com.sample.hazesoftnews.domain.model.Articles
import com.sample.hazesoftnews.domain.use_case.get_news.GetNewsUseCase
import com.sample.hazesoftnews.domain.use_case.get_news_by_query.GetNewsByQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val getNewsByQueryUseCase: GetNewsByQueryUseCase
) : ViewModel() {

    private val _state = mutableStateOf<NewsListState>(NewsListState())
    val state: State<NewsListState> = _state

    init {
       // getNewsByQuery("tesla")
        getNews()
    }

    private fun getNews() {
        getNewsUseCase().onEach { result ->

            when(result){
                is Resource.Success->{
                    _state.value = NewsListState(news = result.data?: Articles(emptyList(),"",0))
                }
                is Resource.Error->{
                    _state.value = NewsListState(
                        error = result.message?:"An unexpected error occurred"
                    )
                }
                is Resource.Loading->{
                    _state.value= NewsListState(isLoading = true)

                }
            }

        }.launchIn(viewModelScope)
    }


    private fun getNewsByQuery(query:String) {
        getNewsByQueryUseCase(query).onEach { result ->

            when(result){
                is Resource.Success->{
                    _state.value = NewsListState(news = result.data?: Articles(emptyList(),"",0))
                }
                is Resource.Error->{
                    _state.value = NewsListState(
                        error = result.message?:"An unexpected error occurred"
                    )
                }
                is Resource.Loading->{
                    _state.value= NewsListState(isLoading = true)

                }
            }

        }.launchIn(viewModelScope)
    }

}