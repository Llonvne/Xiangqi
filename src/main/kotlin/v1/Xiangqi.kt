package v1

import v1.Code.decode
import v1.board.*

class Xiangqi {
    fun beginFromCode(code: String = standardCode) {
        val board = BoardRecorder(decode(code))
        start(board)
    }

    fun beginFromFile(fileName: String) {
        val board = BoardRecorder.fromFile(fileName)
        start(board)
    }

    private fun start(board: BoardRecorder) {
        while (board.getGameStatus() == GameStatus.Running) {

            printBoard(board.pieceMap())

            var movement = requireMovement()

            if (movement.normal == -1) {
                board.toFile(readln())
                break;
            }

//            while (!board.isValidMovement(movement)) {
//                movement = v1.requireMovement()
//            }

            board.applyMovement(movement)
        }
    }
}

fun requireMovement(): Movement {
    val nums = readln().trim().split(" ").map { it.toInt() }
    if (nums.size == 1) {
        return Movement(Point(0, 0), Point(0, 0), nums[0])
    }
    return Movement(Point(nums[0], nums[1]), Point(nums[2], nums[3]))
}