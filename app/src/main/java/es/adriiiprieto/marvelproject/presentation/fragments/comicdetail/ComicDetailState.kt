package es.adriiiprieto.marvelproject.presentation.fragments.comicdetail

import es.adriiiprieto.marvelproject.base.BaseViewState
import es.adriiiprieto.marvelproject.data.marvel.model.Comic

data class ComicDetailState(
    val comic: Comic? = null
) : BaseViewState()