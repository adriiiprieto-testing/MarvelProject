package es.adriiiprieto.marvelproject.presentation.fragments.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.adriiiprieto.marvelproject.data.MarvelRepository
import kotlinx.coroutines.launch

class CharacterListViewModel: ViewModel() {

    fun requestInformation() {
        viewModelScope.launch {
            MarvelRepository().getAllCharacters()
        }
    }

}