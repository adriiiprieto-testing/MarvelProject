package es.adriiiprieto.marvelproject.presentation.fragments.comicdetail

import es.adriiiprieto.marvelproject.base.BaseViewModel
import es.adriiiprieto.marvelproject.domain.repository.MarvelRepository
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class ComicDetailViewModel : BaseViewModel<ComicDetailState>(), KoinComponent {

    private val repository by inject<MarvelRepository>()

    override val defaultState: ComicDetailState = ComicDetailState()

    override fun onStartFirstTime() {

    }

    fun requestInformation(id: Int) {
        updateToLoadingState()

        executeCoroutines({

            val comic = repository.getComic(id)
            updateToNormalState(ComicDetailState(comic))

        }, { error ->

            updateToErrorState(error)

        })
    }

}