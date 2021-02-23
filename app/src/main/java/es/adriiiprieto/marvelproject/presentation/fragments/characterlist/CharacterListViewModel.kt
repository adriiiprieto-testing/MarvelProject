package es.adriiiprieto.marvelproject.presentation.fragments.characterlist

import es.adriiiprieto.marvelproject.base.BaseViewModel
import es.adriiiprieto.marvelproject.data.MarvelRepository

class CharacterListViewModel : BaseViewModel<CharacterListState>() {

    override val defaultState: CharacterListState = CharacterListState()

    override fun onStartFirstTime() {
    }

    private fun requestInformation() {
        updateToLoadingState(CharacterListState(listOf()))

        checkDataState { state ->
            executeCoroutines({
                val response = MarvelRepository().getAllCharacters(state.limit)
                updateToNormalState(CharacterListState(response))
            }, { error ->
                updateToErrorState(CharacterListState(listOf()), error)
            })
        }
    }

    fun onActionChangeSpinnerValue(limit: String) {
        // TODO: Update state and call request information
        requestInformation()
    }
}