package es.adriiiprieto.marvelproject.presentation.fragments.characterlist

import es.adriiiprieto.marvelproject.base.BaseViewState
import es.adriiiprieto.marvelproject.data.marvel.model.Character

data class CharacterListState(
    val characterList: List<Character> = listOf(),
    val limit: Int = 20,
    val character: Character? = null
) : BaseViewState()