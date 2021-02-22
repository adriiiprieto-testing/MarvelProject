package es.adriiiprieto.marvelproject.presentation.fragments.characterlist

import es.adriiiprieto.marvelproject.base.BaseViewModel
import es.adriiiprieto.marvelproject.data.MarvelRepository

class CharacterListViewModel : BaseViewModel<CharacterListState>() {

    fun requestInformation(limit: Int = 20) {
        updateToLoadingState(CharacterListState(listOf()))

        executeCoroutines({
            val response = MarvelRepository().getAllCharacters(limit)

            updateToNormalState(CharacterListState(response))
        }, { error ->
            updateToErrorState(CharacterListState(listOf()), error)
        })
    }

    fun onActionChangeSpinnerValue(limit: String) {
        requestInformation(limit.toInt())
    }

}