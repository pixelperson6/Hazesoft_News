package com.sample.hazesoftnews.domain.use_case.get_news


import com.sample.hazesoftnews.common.Resource
import com.sample.hazesoftnews.data.remote.dto.toArticles
import com.sample.hazesoftnews.domain.model.Articles
import com.sample.hazesoftnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository:NewsRepository
) {
    operator fun invoke():Flow<Resource<Articles>> = flow{
        try {
            emit(Resource.Loading<Articles>())
            val articles = repository.getNews().toArticles()
            emit(Resource.Success<Articles>(articles))
        } catch (e:HttpException){
            emit(Resource.Error<Articles>(e.localizedMessage ?:"An unexpected error occurred"))
        } catch (e:IOException){
            emit(Resource.Error<Articles>("Couldn't reach server. Check your internet connection"))

        }
    }
}