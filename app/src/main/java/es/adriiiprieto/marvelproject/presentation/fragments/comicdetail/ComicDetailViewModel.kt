package es.adriiiprieto.marvelproject.presentation.fragments.comicdetail

import es.adriiiprieto.marvelproject.base.BaseViewModel
import es.adriiiprieto.marvelproject.data.MarvelRepository

class ComicDetailViewModel : BaseViewModel<ComicDetailState>() {

    override val defaultState: ComicDetailState = ComicDetailState()

    override fun onStartFirstTime() {

    }

    fun requestInformation(id: Int) {
        updateToLoadingState()

        executeCoroutines({

            val comic = MarvelRepository().getComic(id)
            updateToNormalState(ComicDetailState(comic))

        }, { error ->

            updateToErrorState(error)

        })
    }

}