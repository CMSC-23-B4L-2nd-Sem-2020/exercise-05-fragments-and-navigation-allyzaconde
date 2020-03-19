package com.example.conde_exer5_lightsoutv2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.conde_exer5_lightsoutv2.databinding.FragmentEndGameBinding

/**
 * A simple [Fragment] subclass.
 */
class EndGameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentEndGameBinding>(inflater,
            R.layout.fragment_end_game,container,false)

        binding.retryButton.setOnClickListener{
                view : View ->
            view.findNavController().navigate(R.id.action_endGameFragment_to_gameFragment)
        }

        return binding.root
    }

}
