package es.adriiiprieto.marvelproject.presentation.fragments.characterdetail

import es.adriiiprieto.marvelproject.data.model.Character
import java.io.Serializable

data class CharacterDetailState(
    val character: Character? = null
) : Serializable