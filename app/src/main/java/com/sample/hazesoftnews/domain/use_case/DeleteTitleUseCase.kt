package com.sample.hazesoftnews.domain.use_case


import com.sample.hazesoftnews.domain.model.Title
import com.sample.hazesoftnews.domain.repository.TitleRepository

class DeleteTitleUseCase(
    private val repository: TitleRepository
) {
    suspend operator fun invoke(title: Title){
        repository.deleteTitle(title = title)
    }
}