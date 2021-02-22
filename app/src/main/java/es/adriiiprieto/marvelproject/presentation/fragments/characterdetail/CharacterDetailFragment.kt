package es.adriiiprieto.marvelproject.presentation.fragments.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import es.adriiiprieto.marvelproject.R
import es.adriiiprieto.marvelproject.base.BaseExtraData
import es.adriiiprieto.marvelproject.base.BaseFragment
import es.adriiiprieto.marvelproject.base.BaseState
import es.adriiiprieto.marvelproject.data.NoCharacterException
import es.adriiiprieto.marvelproject.databinding.CharacterDetailFragmentBinding


class CharacterDetailFragment : BaseFragment<CharacterDetailViewModel, CharacterDetailFragmentBinding>() {

    /**
     * Base variables
     */
    override val viewModelClass = CharacterDetailViewModel::class.java

    private lateinit var vm: CharacterDetailViewModel

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> CharacterDetailFragmentBinding = CharacterDetailFragmentBinding::inflate

    /**
     * Base functions
     */
    override fun setupView(viewModel: CharacterDetailViewModel) {
        vm = viewModel
    }



    /**
     * Old to remove
     */
    private val args: CharacterDetailFragmentArgs by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        vm.getState().observe(viewLifecycleOwner, { state ->
            when (state) {
                is BaseState.Normal -> onNormal(state.data as CharacterDetailState)
                is BaseState.Loading -> onLoading(state.dataLoading)
                is BaseState.Error -> onError(state.dataError)
            }
        })

        vm.requestInformation(args.characterId)

        return binding.root
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


            binding.characterDetailFragmentViewPager.adapter = CharacterDetailViewPagerAdapter(this, character)
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



}