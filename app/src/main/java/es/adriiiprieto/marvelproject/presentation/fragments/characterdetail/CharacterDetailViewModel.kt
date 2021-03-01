package es.adriiiprieto.marvelproject.presentation.fragments.characterdetail

import es.adriiiprieto.marvelproject.base.BaseViewModel
import es.adriiiprieto.marvelproject.domain.repository.MarvelRepository

class CharacterDetailViewModel(private val repository: MarvelRepository) : BaseViewModel<CharacterDetailState>() {

    override val defaultState: CharacterDetailState = CharacterDetailState()

    override fun onStartFirstTime() {

    }


    fun requestInformation(characterId: Int) {
        updateToLoadingState()

        executeCoroutines({
            val response = repository.getCharacter(characterId)
            updateToNormalState(CharacterDetailState(response))
        }, { error ->
            updateToErrorState(error)
        })
    }

}