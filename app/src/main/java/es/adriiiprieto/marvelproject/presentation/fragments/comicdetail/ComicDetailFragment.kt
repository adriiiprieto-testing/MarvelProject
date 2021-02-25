package es.adriiiprieto.marvelproject.presentation.fragments.comicdetail

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import es.adriiiprieto.marvelproject.R
import es.adriiiprieto.marvelproject.base.BaseExtraData
import es.adriiiprieto.marvelproject.base.BaseFragment
import es.adriiiprieto.marvelproject.databinding.ComicDetailFragmentBinding

class ComicDetailFragment : BaseFragment<ComicDetailState, ComicDetailViewModel, ComicDetailFragmentBinding>() {

    override val viewModelClass: Class<ComicDetailViewModel> = ComicDetailViewModel::class.java

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ComicDetailFragmentBinding = ComicDetailFragmentBinding::inflate

    lateinit var myViewModel: ComicDetailViewModel

    val args: ComicDetailFragmentArgs by navArgs()

    override fun setupView(viewModel: ComicDetailViewModel) {
        myViewModel = viewModel

        myViewModel.requestInformation(args.comicId)
    }

    override fun onNormal(data: ComicDetailState) {
        binding.comicDetailFragmentProgressBar.visibility = View.GONE
        binding.comicDetailFragmentTextDescription.setTextColor(Color.BLACK)

        binding.comicDetailFragmentTextViewName.text = data.comic?.title ?: getString(R.string.comicDetailFragmentTextNoTitle)
        binding.comicDetailFragmentTextDescription.text = data.comic?.description ?: getString(R.string.comicDetailFragmentTextDescriptionNotAvailable)
    }

    override fun onLoading(dataLoading: BaseExtraData?) {
        binding.comicDetailFragmentProgressBar.visibility = View.VISIBLE
    }

    override fun onError(dataError: Throwable) {
        binding.comicDetailFragmentTextDescription.setTextColor(Color.RED)
        binding.comicDetailFragmentTextDescription.text = getString(R.string.comicDetailFragmentTextErrorMessage, dataError.toString())
    }

}