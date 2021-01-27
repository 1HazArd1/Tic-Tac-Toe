package com.aakash.tic_tac_toe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var PLAYER =true
    private var turnCount=0
    private var GameVictory=false
    private lateinit var board:Array<Array<Button>>
    private var boardStatus= Array(3) { IntArray(3) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board = arrayOf(
            arrayOf(btn1,btn2,btn3),
            arrayOf(btn4,btn5,btn6),
            arrayOf(btn7,btn8,btn9)
        )
            initializeBoardStatus()
        for (i in board){
            for (button in i){
                button.setOnClickListener(this)
            }
        }

        btnReset.setOnClickListener {
            initializeBoardStatus()
            PLAYER=true
            turnCount=0
            GameVictory=false
            tvDisplay.text="Player X Turn"
        }
    }

    private fun initializeBoardStatus() {
        btnReset.isVisible=false
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j]=-1
            }
        }
        for (i in board){
            for (button in i){
                button.isEnabled=true
                button.text=""
            }
        }
    }


    override fun onClick(v: View) {
        when(v.id){
            R.id.btn1->{
                updateDisplay(row=0,col=0,player=PLAYER)
            }
            R.id.btn2->{
                updateDisplay(row=0,col=1,player=PLAYER)
            }
            R.id.btn3->{
                updateDisplay(row=0,col=2,player=PLAYER)
            }

            R.id.btn4->{
                updateDisplay(row=1,col=0,player=PLAYER)
            }
            R.id.btn5->{
                updateDisplay(row=1,col=1,player=PLAYER)
            }
            R.id.btn6-> {
                updateDisplay(row=1,col=2,player=PLAYER)
            }
            R.id.btn7->{
                updateDisplay(row=2,col=0,player=PLAYER)
            }
            R.id.btn8->{
                updateDisplay(row=2,col=1,player=PLAYER)
            }
            R.id.btn9 ->{
                updateDisplay(row=2,col=2,player=PLAYER)
            }

        }
        turnCount++
        PLAYER=!PLAYER
        if(PLAYER){
            tvDisplay.text="Player X turn"
        }
        else{
            tvDisplay.text="Player O turn"
        }
        if(turnCount==9){
            btnReset.isVisible=true
            tvDisplay.text="Game is Drawn!"
            btnDisable()
        }
        checkWinner()
    }
    private fun checkWinner(){
        GameVictory=true

        //Horizontal winner check
        for(i in 0..2){
            if (boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][1]==boardStatus[i][2]){
                if(board[i][0].text=="X"){
                    tvDisplay.text="Congrats X has WON!"
                    btnReset.isVisible=true
                    btnDisable()
                    break
                }
                else if (board[i][0].text=="O"){
                    tvDisplay.text="Congrats O has WON!"
                    btnReset.isVisible=true
                    btnDisable()
                    break
                }
            }
        }

        //Vertical winner check
        for(i in 0..2){
            if (boardStatus[0][i]==boardStatus[1][i] && boardStatus[1][i]==boardStatus[2][i]){
                if(board[0][i].text=="X"){
                    tvDisplay.text="Congrats X has WON!"
                    btnReset.isVisible=true
                    btnDisable()
                    break
                }
                else if (board[0][i].text=="O"){
                    tvDisplay.text="Congrats O has WON!"
                    btnReset.isVisible=true
                    btnDisable()
                    break
                }
            }
        }
        //Diagonal winner check
        if(boardStatus[0][0]==boardStatus[1][1]&&boardStatus[1][1]==boardStatus[2][2]){
            if(board[0][0].text=="X"){
                tvDisplay.text="Congrats! X has Won"
                btnReset.isVisible=true
                btnDisable()
            }
            if(board[0][0].text=="O"){
                tvDisplay.text="Congrats! O has Won"
                btnReset.isVisible=true
                btnDisable()
            }
        }
        if(boardStatus[0][2]==boardStatus[1][1]&&boardStatus[1][1]==boardStatus[2][0]){
            if(board[0][2].text=="X"){
                tvDisplay.text="Congrats! X has Won"
                btnReset.isVisible=true
                btnDisable()
            }
            if(board[0][2].text=="O"){
                tvDisplay.text="Congrats! O has Won"
                btnReset.isVisible=true
                btnDisable()
            }
        }

    }

    private fun btnDisable() {
        for(i in board){
            for(button in i){
                button.isEnabled=false
            }
        }
    }

    private fun updateDisplay(row: Int, col: Int,player:Boolean) {
         val text=if (player)"X" else "O"
         val value=if (player)1 else 0
         board[row][col].apply {
           isEnabled=false
             setText(text)
         }
        boardStatus[row][col]=value
    }


}