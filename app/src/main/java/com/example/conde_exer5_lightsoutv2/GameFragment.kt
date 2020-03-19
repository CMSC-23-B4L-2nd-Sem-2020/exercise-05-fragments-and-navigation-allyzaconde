package com.example.conde_exer5_lightsoutv2

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.conde_exer5_lightsoutv2.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private var moveCount:Int = 0
    private lateinit var moveCountView:TextView
    private val array: IntArray = intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0) //array of the 5x5 grid, 0 as lit up and 1 as lights out
    private var check:Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game, container, false)

        binding.buttonDone.setOnClickListener {
            updateNickname(it) //updates the nickname after clicking the done button
        }

        binding.viewNickname.setOnClickListener{
            changeNickname(it)
        }

        binding.buttonReset.setOnClickListener{
            retry(it)
        }
        moveCountView = binding.viewCount //shows on screen the number of clicks the user had

        setListeners() //sets all the listeners on clicking one of the 5x5 lights out array
        return binding.root
    }

    private fun updateNickname(view: View) { //receives the info inputted by the user and gives it to the view_nickname textview
        val nicknameEditText = binding.editNickname
        val nicknameTextView = binding.viewNickname

        nicknameTextView.text = nicknameEditText.text

        nicknameEditText.visibility = View.GONE
        view.visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE

        //closes the keyboard after pressing the done button
        val inputMethodManager = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun changeNickname(view: View) { //makes the edit text and done button appear to update the nickname
        val nicknameEditText = binding.editNickname
        val nicknameTextView = binding.viewNickname
        val nicknameButton = binding.buttonDone

        nicknameEditText.visibility = View.VISIBLE
        nicknameButton.visibility = View.VISIBLE
        nicknameTextView.visibility = View.GONE
    }

    private fun getId(int: Int): TextView{ //holds all the ids of each boxes in a list for easy access
        val list: List<TextView> = listOf(
            binding.box1,binding.box2,binding.box3,binding.box4,binding.box5,
            binding.box6,binding.box7,binding.box8,binding.box9,binding.box10,
            binding.box11,binding.box12,binding.box13,binding.box14,binding.box15,
            binding.box16,binding.box17,binding.box18,binding.box19,binding.box20,
            binding.box21,binding.box22,binding.box23,binding.box24,binding.box25
        )
        return list[int]
    }

    private fun setListeners(){ //sets listeners to each box
        for (item in (0..24)){
                getId(item).setOnClickListener{
                closeLights(it)
            }
        }
    }

    private fun closeLights(view: View){ //does the function of switching the lights on or off in the code
        if(moveCount == 0){ //checks if the first move hasn't been done yet, and it will show the move counter
            val show = binding.viewCount
            show.visibility = View.VISIBLE
        }
        moveCount++ //adds one move every click of the box
        moveCountView.text = moveCount.toString()

        if(moveCount == 1){ //checks if move or moves is to be used (for grammar purposes)
            moveCountView.append(" Move\n")
        }else{
            moveCountView.append(" Moves\n")
        }

        for(item in (0..24)){ //checks which box the user clicked and it will use the checkLights function (the actual code for turning on or off the lights)
            if(getId(item) == view){
                checkLights(item)
            }
        }
        if(check){ //checks if the game has been won and it will reset the game
            retry(view)
            check = false
        }
        check(view) //checks if the game is solved
    }

    private fun checkLights(int:Int){
        if(array[int] == 0){ //for checking the light of the middle box
            array[int] = 1
            getId(int).setBackgroundColor(Color.DKGRAY)
        }else{
            array[int] = 0
            getId(int).setBackgroundColor(Color.CYAN)
        }

        if(int<24){ //keeps the code from accessing the array greater than 24
            if(array[int+1] == 0 && ((int+1)%5)!=0){ //for checking the light of the right box
                array[int+1] = 1
                getId(int+1).setBackgroundColor(Color.DKGRAY)
            }else if(array[int+1] == 1 && ((int+1)%5)!=0){
                array[int+1] = 0
                getId(int+1).setBackgroundColor(Color.CYAN)
            }
        }

        if(int>0){ //keeps the code from accessing the array less than 0
            if(array[int-1] == 0 && (int%5)!=0){ //for checking the light of the left box
                array[int-1] = 1
                getId(int-1).setBackgroundColor(Color.DKGRAY)
            }else if(array[int-1] == 1 && (int%5)!=0){
                array[int-1] = 0
                getId(int-1).setBackgroundColor(Color.CYAN)
            }
        }

        if(int>4){ //keeps the code from accessing the array less than 4
            if(array[int-5] == 0){ //for checking the light of the top box
                array[int-5] = 1
                getId(int-5).setBackgroundColor(Color.DKGRAY)
            }else{
                array[int-5] = 0
                getId(int-5).setBackgroundColor(Color.CYAN)
            }
        }

        if(int<20){ //keeps the code from accessing the array greater than 20
            if(array[int+5] == 0){ //for checking the light of the lower box
                array[int+5] = 1
                getId(int+5).setBackgroundColor(Color.DKGRAY)
            }else{
                array[int+5] = 0
                getId(int+5).setBackgroundColor(Color.CYAN)
            }
        }
    }

    private fun retry(view: View){ //resets the whole game anew
        moveCount = 0
        val show = binding.viewCount
        show.visibility = View.GONE
        for(item in (0..24)){
            getId(item).setBackgroundColor(Color.CYAN)
            array[item] = 0
        }
    }

    @SuppressLint("ResourceType")
    private fun check(view: View){ //checks if the game is solved
        var counter = 0
        for(i in (0..24)){
            if(array[i]==1){
                counter++
            }
        }
        if(counter == 25){
//            val winner:TextView = binding.viewCount
//            winner.setText(R.string.winner)
//            winner.append(" ")
//            winner.append(moveCount.toString())
//            winner.append(" moves :)")
            view.findNavController().navigate(R.id.action_gameFragment_to_endGameFragment)
            check = true
        }else{
            return
        }

    }
}
