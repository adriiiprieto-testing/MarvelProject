package es.adriiiprieto.marvelproject.presentation.fragments.characterlist

import es.adriiiprieto.marvelproject.data.model.Character
import java.io.Serializable

data class CharacterListState(
    val characterList: List<Character> = listOf()
) : Serializable