package es.adriiiprieto.marvelproject.presentation.fragments.characterdetail

import dagger.hilt.android.lifecycle.HiltViewModel
import es.adriiiprieto.marvelproject.base.BaseViewModel
import es.adriiiprieto.marvelproject.base.util.NetworkManager
import es.adriiiprieto.marvelproject.domain.repository.MarvelRepository
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val repository: MarvelRepository) : BaseViewModel<CharacterDetailState>() {

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