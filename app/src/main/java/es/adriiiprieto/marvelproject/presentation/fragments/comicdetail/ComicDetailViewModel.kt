package es.adriiiprieto.marvelproject.presentation.fragments.comicdetail

import dagger.hilt.android.lifecycle.HiltViewModel
import es.adriiiprieto.marvelproject.base.BaseViewModel
import es.adriiiprieto.marvelproject.domain.repository.MarvelRepository
import javax.inject.Inject

@HiltViewModel
class ComicDetailViewModel @Inject constructor(private val repository: MarvelRepository) : BaseViewModel<ComicDetailState>() {

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