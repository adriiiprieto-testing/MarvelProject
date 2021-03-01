package es.adriiiprieto.marvelproject.presentation.fragments.characterdetail

import es.adriiiprieto.marvelproject.base.BaseViewState
import es.adriiiprieto.marvelproject.data.marvel.model.Character

data class CharacterDetailState(
    val character: Character? = null
) : BaseViewState()