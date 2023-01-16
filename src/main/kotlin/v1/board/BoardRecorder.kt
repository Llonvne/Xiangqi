package v1.board

import v1.Code.decode
import v1.Code.encode
import com.alibaba.fastjson2.into
import com.alibaba.fastjson2.toJSONString
import java.io.File
import java.util.*

class BoardRecorder(val board: Board) : AbstractBoard by board {

    var initialBoardCode = encode(board)

    var history = mutableListOf<Movement>()

    override fun applyMovement(movement: Movement) {
        board.applyMovement(movement)
        history.add(movement)
    }

    fun toFile(filename: String) {
        val f = File(filename)
        f.createNewFile()
        Base64.getEncoder().wrap(f.outputStream()).bufferedWriter().use {
            it.write(initialBoardCode)
            it.newLine()
            it.write(encode(board))
            it.newLine()
            it.write(history.toJSONString())
        }
    }

    companion object {

        fun fromFile(filename: String): BoardRecorder {
            val f = File(filename)

            Base64.getDecoder().wrap(f.inputStream()).bufferedReader().use {
                val initialCode = it.readLine()
                val now = it.readLine()
                val list = it.readText().into<MutableList<Movement>>()
                val board = decode(now)
                val recorder = BoardRecorder(board)
                recorder.initialBoardCode = initialCode
                recorder.history = list
                return recorder
            }
        }
    }
}