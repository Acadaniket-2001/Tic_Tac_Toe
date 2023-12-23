package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.graphics.text.TextRunShaper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import java.util.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

var res: TextView? = null

class MainActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {
    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.tic_tac_toe)
        var n = 3
        var moves = n*n
        var board = Array(n) {Array(n) {'-'} }
        var x: Int = 0
        var y: Int = 0

        var turn: Int = 0

        var v00:TextView = findViewById(R.id.view00)
        var v01:TextView = findViewById(R.id.view01)
        var v02:TextView = findViewById(R.id.view02)
        var v10:TextView = findViewById(R.id.view10)
        var v11:TextView = findViewById(R.id.view11)
        var v12:TextView = findViewById(R.id.view12)
        var v20:TextView = findViewById(R.id.view20)
        var v21:TextView = findViewById(R.id.view21)
        var v22:TextView = findViewById(R.id.view22)
        var reset: Button = findViewById(R.id.reset_button)
        var p1: TextView = findViewById(R.id.player_label_1)
        var p2: TextView = findViewById(R.id.player_label_2)

        var arr = arrayOf(v00, v01, v02, v10, v11, v12, v20, v21, v22)

        p1.setTextColor(ContextCompat.getColor(this, R.color.accent_color))
        p1.setTypeface(null, Typeface.BOLD)

        reset.setOnClickListener{

            v00.isEnabled = true
            v01.isEnabled = true
            v02.isEnabled = true
            v10.isEnabled = true
            v11.isEnabled = true
            v12.isEnabled = true
            v20.isEnabled = true
            v21.isEnabled = true
            v22.isEnabled = true

            v00.text = ""
            v01.text = ""
            v02.text = ""
            v10.text = ""
            v11.text = ""
            v12.text = ""
            v20.text = ""
            v21.text = ""
            v22.text = ""
            res?.text = "START"
            for(i in 0 until n) {
                for(j in 0 until n) {
                    board[i][j] = '-'
                }
            }
            toggle_player(p1, p2, 0)
            turn = 0
        }

        res = findViewById(R.id.result_field)

        v00.setOnClickListener {
            v00.text = if(turn % 2 == 0) "X" else "O"
            move(board, 0, 0, turn, arr)
            turn++
            toggle_player(p1, p2, turn)
            v00.isEnabled = false
        }

        v01.setOnClickListener {
            v01.text = if(turn % 2 == 0) "X" else "O"
            move(board, 0, 1, turn, arr)
            turn++
            toggle_player(p1, p2, turn)
            v01.isEnabled = false
        }

        v02.setOnClickListener {
            v02.text = if(turn % 2 == 0) "X" else "O"
            move(board, 0, 2, turn, arr)
            turn++
            toggle_player(p1, p2, turn)
            v02.isEnabled = false
        }

        v10.setOnClickListener {
            v10.text = if(turn % 2 == 0) "X" else "O"
            move(board, 1, 0, turn, arr)
            turn++
            toggle_player(p1, p2, turn)
            v10.isEnabled = false
        }

        v11.setOnClickListener {
            v11.text = if(turn % 2 == 0) "X" else "O"
            move(board, 1, 1, turn, arr)
            turn++
            toggle_player(p1, p2, turn)
            v11.isEnabled = false
        }

        v12.setOnClickListener {
            v12.text = if(turn % 2 == 0) "X" else "O"
            move(board, 1, 2, turn, arr)
            turn++
            toggle_player(p1, p2, turn)
            v12.isEnabled = false
        }

        v20.setOnClickListener {
            v20.text = if(turn % 2 == 0) "X" else "O"
            move(board, 2, 0, turn, arr)
            turn++
            toggle_player(p1, p2, turn)
            v20.isEnabled = false
        }

        v21.setOnClickListener {
            v21.text = if(turn % 2 == 0) "X" else "O"
            move(board, 2, 1, turn, arr)
            turn++
            toggle_player(p1, p2, turn)
            v21.isEnabled = false
        }

        v22.setOnClickListener {
            v22.text = if(turn % 2 == 0) "X" else "O"
            move(board, 2, 2, turn, arr)
            turn++
            toggle_player(p1, p2, turn)
            v22.isEnabled = false
        }
    }

    private fun move(board: Array<Array<Char>>, x: Int, y: Int, move: Int, arr: Array<TextView>): Boolean {

        var sz: Int = board.size
        if(board[x][y] != '-') {
//            println("NOT FEASIBLE")
            res?.text = "NOT_FEASIBLE"
            return false
        }

        println("true")
        board[x][y] = if(move % 2 == 1) 'O' else 'X'

        var is_tied = true
        for(i in 0 until sz) {
            for(j in 0 until sz) {
                print("| ${board[i][j]} |")
                if(board[i][j] == '-')  is_tied = false
            }
            println("");
        }

        // checking for winner
        // for rows
        var f = true
        for(i in 0..sz-1) {
            f = true
            for(j in 0..sz-2) {
                if(board[i][j] != board[i][j + 1]) {
                    f = false
                    break
                }
            }
            if(f && (board[i][0] != '-')) {
                result(move, arr)
                return true
            }
        }

        // for cols
        f = true
        for(j in 0..sz-1) {
            f = true
            for(i in 0..sz-2) {
                if(board[i][j] != board[i + 1][j]) {
                    f = false
                    break
                }
            }
            if(f && (board[0][j] != '-')) {
                result(move, arr)
                return true
            }
        }

        f = true
        var i: Int = 0
        var j: Int = 0
        while(i < sz - 1 && j < sz - 1) {
            if(board[i][j] != board[i + 1][j + 1]) {
                f = false
                break;
            }
            i++
            j++
        }
        if(f && (board[0][0] != '-')) {
            result(move, arr)
            return true
        }

        f = true
        i = 0
        j = sz - 1
        while(i < sz - 1 && j >= 1) {
            if(board[i][j] != board[i + 1][j - 1]) {
                f = false
                break
            }
            i++
            j--
        }
        if(f && (board[0][sz - 1] != '-')) {
            result(move, arr)
            return true
        }

        if(is_tied) {
//            println("DRAW")
            res?.text = "DRAW"
        }
        else {
//            println("ONGOING")
            res?.text = "ONGOING"
        }
        return true
    }

    private fun toggle_player(p1: TextView, p2: TextView, move: Int) {
        if(move % 2 == 0) {
            p1.setTextColor(ContextCompat.getColor(this, R.color.accent_color))
            p1.setTypeface(null, Typeface.BOLD)

            p2.setTextColor(ContextCompat.getColor(this, R.color.primary_color))
            p2.setTypeface(null, Typeface.NORMAL)
        }
        else {
            p2.setTextColor(ContextCompat.getColor(this, R.color.accent_color))
            p2.setTypeface(null, Typeface.BOLD)

            p1.setTextColor(ContextCompat.getColor(this, R.color.primary_color))
            p1.setTypeface(null, Typeface.NORMAL)
        }
    }

    private fun result(move: Int, arr: Array<TextView>) {
        if(move % 2 == 0) res?.text = "X player Won"
        else res?.text = "O player Won"
        for(i in arr.indices) {
            arr[i].isEnabled = false
        }
    }
}

