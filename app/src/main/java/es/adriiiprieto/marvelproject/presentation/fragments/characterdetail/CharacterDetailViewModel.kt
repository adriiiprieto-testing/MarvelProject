package es.adriiiprieto.marvelproject.presentation.fragments.characterdetail

import es.adriiiprieto.marvelproject.base.BaseViewModel
import es.adriiiprieto.marvelproject.data.MarvelRepository

class CharacterDetailViewModel : BaseViewModel<CharacterDetailState>() {

    override val defaultState: CharacterDetailState = CharacterDetailState()

    override fun onStartFirstTime() {

    }


    fun requestInformation(characterId: Int) {
        updateToLoadingState()
        executeCoroutines({
            val response = MarvelRepository().getCharacter(characterId)
            updateToNormalState(CharacterDetailState(response))
        }, { error ->
            updateToErrorState(error)
        })
    }

}