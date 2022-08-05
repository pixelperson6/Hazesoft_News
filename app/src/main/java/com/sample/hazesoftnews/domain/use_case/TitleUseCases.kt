package com.sample.hazesoftnews.domain.use_case

data class TitleUseCases(
    val getTitles:GetTitlesUseCase,
    val deleteTitle:DeleteTitleUseCase,
    val addTitle:AddTitleUseCase
)
