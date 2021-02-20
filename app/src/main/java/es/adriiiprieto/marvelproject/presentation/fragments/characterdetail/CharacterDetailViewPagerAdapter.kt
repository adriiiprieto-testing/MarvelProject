package es.adriiiprieto.marvelproject.presentation.fragments.characterdetail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import es.adriiiprieto.marvelproject.data.model.Character
import es.adriiiprieto.marvelproject.presentation.fragments.characterdetail.curriculum.CurriculumFragment

class CharacterDetailViewPagerAdapter(fragment: Fragment, private val character: Character) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> CurriculumFragment(character.comics.items)
            1 -> CurriculumFragment(character.series.items)
            2 -> CurriculumFragment(character.stories.items)
            else -> Fragment()
        }
    }

}