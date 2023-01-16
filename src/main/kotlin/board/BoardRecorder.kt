package board

import Code.decode
import Code.encode
import com.alibaba.fastjson2.into
import com.alibaba.fastjson2.toJSONString
import java.io.File
import java.util.*

class BoardRecorder(val board: Board) : AbstractBoard by board {

    var initialBoardCode = encode(board)

    var history = mutableListOf<History>()

    override fun applyMovement(movement: Movement) {
        board.applyMovement(movement)
        history.add(History(movement, encode(board)))
    }

    fun toFile(filename: String) {
        val f = File("${filename}_${Date().time}_record.txt")
        f.createNewFile()
        f.printWriter().use {
            it.println(initialBoardCode)
            it.println(history.toJSONString())
        }
    }

    companion object {
        data class History(val movement: Movement, val code: String)

        fun fromFile(filename: String): BoardRecorder {
            val f = File(filename)
            f.bufferedReader().use {
                val initialCode = it.readLine()
                val list = it.readText().into<MutableList<History>>()
                val board = decode(list.last().code)
                val recorder = BoardRecorder(board)
                recorder.initialBoardCode = initialCode
                recorder.history = list
                return recorder
            }
        }
    }
}