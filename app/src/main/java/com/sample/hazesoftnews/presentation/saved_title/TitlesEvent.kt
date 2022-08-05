package com.sample.hazesoftnews.presentation.saved_title


import com.sample.hazesoftnews.domain.model.Title

sealed class TitlesEvent{
    data class DeleteTitle(val title :Title): TitlesEvent()
    object RestoreTitle: TitlesEvent()
}
