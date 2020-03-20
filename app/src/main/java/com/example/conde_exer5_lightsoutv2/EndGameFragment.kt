package com.example.conde_exer5_lightsoutv2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
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

        val tv = binding.showData
        tv.text = arguments?.getString("congrats") //takes string from the pair "congrats" from the previous fragment

        return binding.root
    }
}
