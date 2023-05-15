package br.com.lira.rickandmorty.characterdetails.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.lira.rickandmorty.R

private const val ARG_CHARACTER_ID = "char_id"

class CharacterDetailsFragment : Fragment() {
    private var characterId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterId = it.getLong(ARG_CHARACTER_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_details, container, false)
    }

    companion object {
        fun newInstance(characterId: Long) =
            CharacterDetailsFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_CHARACTER_ID, characterId)
                }
            }
    }
}