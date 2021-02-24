package es.adriiiprieto.marvelproject.presentation.fragments.comicdetail

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import es.adriiiprieto.marvelproject.base.BaseExtraData
import es.adriiiprieto.marvelproject.base.BaseFragment
import es.adriiiprieto.marvelproject.databinding.ComicDetailFragmentBinding

class ComicDetailFragment : BaseFragment<ComicDetailState, ComicDetailViewModel, ComicDetailFragmentBinding>() {

    override val viewModelClass: Class<ComicDetailViewModel> = ComicDetailViewModel::class.java

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ComicDetailFragmentBinding = ComicDetailFragmentBinding::inflate

    private val args: ComicDetailFragmentArgs by navArgs()

    override fun setupView(viewModel: ComicDetailViewModel) {
        Log.e("Mis argumentos", args.comicId.toString())
    }

    override fun onNormal(data: ComicDetailState) {

    }

    override fun onLoading(dataLoading: BaseExtraData?) {

    }

    override fun onError(dataError: Throwable) {

    }
}