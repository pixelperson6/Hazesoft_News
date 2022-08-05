package com.sample.hazesoftnews.domain.use_case

import com.sample.hazesoftnews.domain.model.InvalidTitleException
import com.sample.hazesoftnews.domain.model.Title
import com.sample.hazesoftnews.domain.repository.TitleRepository


class AddTitleUseCase(
    private val repository: TitleRepository
) {
    @Throws(InvalidTitleException::class)
    suspend operator fun invoke(title: Title){
        if (title.title.isBlank()){
            throw InvalidTitleException("The title can't be empty.")
        }

        repository.insertTitle(title)
    }
}