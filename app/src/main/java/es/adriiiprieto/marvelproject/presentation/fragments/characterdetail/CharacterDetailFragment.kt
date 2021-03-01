package es.adriiiprieto.marvelproject.presentation.fragments.characterdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import es.adriiiprieto.marvelproject.R
import es.adriiiprieto.marvelproject.base.BaseExtraData
import es.adriiiprieto.marvelproject.base.BaseFragment
import es.adriiiprieto.marvelproject.data.marvel.repository.NoCharacterException
import es.adriiiprieto.marvelproject.databinding.CharacterDetailFragmentBinding


class CharacterDetailFragment : BaseFragment<CharacterDetailState, CharacterDetailViewModel, CharacterDetailFragmentBinding>() {

    /**
     * Base variables
     */
    override val viewModelClass = CharacterDetailViewModel::class.java

    private lateinit var vm: CharacterDetailViewModel

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> CharacterDetailFragmentBinding = CharacterDetailFragmentBinding::inflate

    /**
     * Custom variables
     */
    private val args: CharacterDetailFragmentArgs by navArgs()

    /**
     * Base functions
     */
    override fun setupView(viewModel: CharacterDetailViewModel) {
        vm = viewModel

        vm.requestInformation(args.characterId)
    }

    /**
     * State management
     */
    override fun onNormal(data: CharacterDetailState) {
        data.character?.let { character ->
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

            binding.characterDetailFragmentViewPager.adapter = CharacterDetailViewPagerAdapter(this, character) {
                findNavController().navigate(CharacterDetailFragmentDirections.actionCharacterDetailFragmentToComicDetailFragment(it))
            }
            TabLayoutMediator(binding.characterDetailFragmentTabLayout, binding.characterDetailFragmentViewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> getString(R.string.CharacterDetailFragmentTabTitleComics)
                    1 -> getString(R.string.CharacterDetailFragmentTabTitleSeries)
                    2 -> getString(R.string.CharacterDetailFragmentTabTitleStories)
                    else -> ""
                }
            }.attach()

        }
    }

    override fun onLoading(dataLoading: BaseExtraData?) {

    }

    override fun onError(dataError: Throwable) {
        when (dataError) {
            is NoCharacterException -> {

            }
            else -> {

            }
        }
    }
}