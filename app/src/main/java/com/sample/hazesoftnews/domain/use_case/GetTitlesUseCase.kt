package com.sample.hazesoftnews.domain.use_case


import com.sample.hazesoftnews.domain.model.Title
import com.sample.hazesoftnews.domain.repository.TitleRepository
import kotlinx.coroutines.flow.Flow

class GetTitlesUseCase(
    private val repository: TitleRepository
) {
    operator fun invoke(
    ): Flow<List<Title>>{
        return repository.getTitles()
    }
}