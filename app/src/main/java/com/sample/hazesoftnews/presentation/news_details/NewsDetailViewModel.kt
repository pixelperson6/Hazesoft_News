package com.sample.hazesoftnews.presentation.news_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sample.hazesoftnews.common.Constants.PARAM_AUTHOR
import com.sample.hazesoftnews.common.Constants.PARAM_CONTENT
import com.sample.hazesoftnews.common.Constants.PARAM_DECS
import com.sample.hazesoftnews.common.Constants.PARAM_IMAGE_URL
import com.sample.hazesoftnews.common.Constants.PARAM_INDEX
import com.sample.hazesoftnews.common.Constants.PARAM_PUBLISHED
import com.sample.hazesoftnews.common.Constants.PARAM_SOURCE_ID
import com.sample.hazesoftnews.common.Constants.PARAM_SOURCE_NAME
import com.sample.hazesoftnews.common.Constants.PARAM_TITLE
import com.sample.hazesoftnews.common.Constants.PARAM_URL
import com.sample.hazesoftnews.data.remote.dto.Article
import com.sample.hazesoftnews.data.remote.dto.Source
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var currentIndex = 0

    private var author = ""
    private var content = ""
    private var desc = ""
    private var published = ""
    private var id = ""
    private var name =""
    private var title = ""
    private var url = ""
    private var urlToImage = ""

    private val _article = mutableStateOf<Article>(Article(author,content,desc,published,Source(id,name),title,url,urlToImage))
    val article: State<Article> = _article

    private val _index = mutableStateOf<Int>(0)
    val index: State<Int> = _index

    private fun currentIndex(index:Int){
        _index.value = index
    }

    init {

        savedStateHandle.get<Int>(PARAM_INDEX)?.let { currentIndex ->
            this.currentIndex = currentIndex
        }
        currentIndex(currentIndex)

/*        savedStateHandle.get<String>(PARAM_TITLE)?.let { title ->
            this.title = title
        }
        savedStateHandle.get<String>(PARAM_CONTENT)?.let { content ->
            this.content = content
        }
        savedStateHandle.get<String>(PARAM_SOURCE_ID)?.let { id ->
            this.id = id
        }
        savedStateHandle.get<String>(PARAM_SOURCE_NAME)?.let { name ->
            this.name = name
        }
        savedStateHandle.get<String>(PARAM_AUTHOR)?.let { author ->
            this.author= author
        }
        savedStateHandle.get<String>(PARAM_URL)?.let { url ->
            this.url = url
        }
        savedStateHandle.get<String>(PARAM_IMAGE_URL)?.let { urlToImage ->
            this.urlToImage = urlToImage
        }
        savedStateHandle.get<String>(PARAM_DECS)?.let { desc ->
            this.desc = desc
        }
        savedStateHandle.get<String>(PARAM_PUBLISHED)?.let { published ->
            this.published = published
        }

        _article.value = Article(author,content,desc,published,Source(id,name),title,url,urlToImage)*/
    }



}