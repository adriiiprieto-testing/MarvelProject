package es.adriiiprieto.marvelproject.presentation.fragments.comicdetail

import es.adriiiprieto.marvelproject.base.BaseViewState
import es.adriiiprieto.marvelproject.data.model.Comic

data class ComicDetailState(
    val comic: Comic? = null
): BaseViewState()