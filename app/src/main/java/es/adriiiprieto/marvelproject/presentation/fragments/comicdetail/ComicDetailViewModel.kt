package es.adriiiprieto.marvelproject.presentation.fragments.comicdetail

import es.adriiiprieto.marvelproject.base.BaseViewModel
import es.adriiiprieto.marvelproject.domain.repository.MarvelRepository

class ComicDetailViewModel(private val repository: MarvelRepository) : BaseViewModel<ComicDetailState>() {

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