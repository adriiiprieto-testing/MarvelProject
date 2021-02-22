package es.adriiiprieto.marvelproject.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.adriiiprieto.marvelproject.presentation.fragments.characterlist.CharacterListState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel<VS : BaseViewState> : ViewModel() {

    private var baseState: BaseState<VS>? = null
    private val observableState: MutableLiveData<BaseState<VS>> = MutableLiveData()
    fun getObservableState(): LiveData<BaseState<VS>> = observableState

    /**
     * State management
     */
    fun updateToNormalState(viewState: VS){
        baseState = BaseState.Normal(viewState)
        observableState.postValue(baseState)
    }

    fun updateToLoadingState(viewState: VS, loadingData: BaseExtraData? = null){
        baseState = BaseState.Loading(viewState, loadingData)
        observableState.postValue(baseState)
    }

    fun updateToErrorState(viewState: VS, errorData: Throwable = Throwable()){
        baseState = BaseState.Error(viewState, errorData)
        observableState.postValue(baseState)
    }

    /**
     * Coroutines
     */
    fun executeCoroutines(
        block: suspend CoroutineScope.() -> Unit,
        exceptionBlock: suspend CoroutineScope.(Throwable) -> Unit
    ){
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                exceptionBlock(e)
            }
        }
    }
}