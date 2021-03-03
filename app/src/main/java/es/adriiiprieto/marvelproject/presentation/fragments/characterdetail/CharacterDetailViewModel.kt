package es.adriiiprieto.marvelproject.presentation.fragments.characterdetail

import es.adriiiprieto.marvelproject.base.BaseViewModel
import es.adriiiprieto.marvelproject.domain.repository.MarvelRepository
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class CharacterDetailViewModel : BaseViewModel<CharacterDetailState>(), KoinComponent {

    private val repository by inject<MarvelRepository>()

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