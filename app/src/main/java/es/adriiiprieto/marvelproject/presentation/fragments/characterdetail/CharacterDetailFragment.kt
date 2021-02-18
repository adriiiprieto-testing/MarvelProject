package es.adriiiprieto.marvelproject.presentation.fragments.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import es.adriiiprieto.marvelproject.base.BaseExtraData
import es.adriiiprieto.marvelproject.base.BaseState
import es.adriiiprieto.marvelproject.data.NoCharacterException
import es.adriiiprieto.marvelproject.databinding.CharacterDetailFragmentBinding


class CharacterDetailFragment : Fragment() {

    private val viewModel: CharacterDetailViewModel by viewModels()

    lateinit var binding: CharacterDetailFragmentBinding

    private val args: CharacterDetailFragmentArgs by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false)

        viewModel.getState().observe(viewLifecycleOwner, { state ->
            when (state) {
                is BaseState.Normal -> onNormal(state.data as CharacterDetailState)
                is BaseState.Loading -> onLoading(state.dataLoading)
                is BaseState.Error -> onError(state.dataError)
            }
        })

        setupView()

        viewModel.requestInformation(args.characterId)

        return binding.root
    }

    private fun setupView() {

    }

    private fun onError(dataError: Throwable) {
        when (dataError) {
            is NoCharacterException -> {

            }
            else -> {

            }
        }
    }

    private fun onLoading(dataLoading: BaseExtraData?) {

    }

    private fun onNormal(characterDetailState: CharacterDetailState) {
        characterDetailState.character?.let { character ->
            binding.characterDetailFragmentTextViewName.text = character.name
            binding.characterDetailFragmentTextDescription.text = character.description

            character.urls.firstOrNull()?.let { link ->
                binding.characterDetailFragmentWebView.loadUrl(link.url.replace("http", "https"))
                binding.characterDetailFragmentWebView.webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        view.loadUrl(url)
                        return false
                    }
                }
            }
        }
    }

}