package es.adriiiprieto.marvelproject.presentation.fragments.characterlist

import es.adriiiprieto.marvelproject.base.BaseViewModel
import es.adriiiprieto.marvelproject.domain.repository.MarvelRepository

class CharacterListViewModel(private val repository: MarvelRepository) : BaseViewModel<CharacterListState>() {

    override val defaultState: CharacterListState = CharacterListState()

    override fun onStartFirstTime() {
        requestInformation()
    }

    private fun requestInformation() {
        updateToLoadingState()

        checkDataState { state ->
            executeCoroutines({
                val response = repository.getAllCharacters(state.limit)
                updateToNormalState(state.copy(characterList = response))
            }, { error ->
                updateToErrorState(error)
            })
        }
    }

    fun onActionChangeSpinnerValue(limit: String) {
        checkDataState { state ->
            if (state.limit != limit.toInt()) {
                updateDataState(state.copy(limit = limit.toInt()))
                requestInformation()
            }
        }
    }
}