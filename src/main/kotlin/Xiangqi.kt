import Code.decode
import board.*

class Xiangqi {
    fun beginFromCode(code: String = standardCode) {
        val board = BoardRecorder(decode(code))
        run(board)
    }

    fun beginFromFile(fileName: String) {
        val board = BoardRecorder.fromFile(fileName)
        run(board)
    }

    fun run(board: BoardRecorder) {
        while (board.getGameStatus() == GameStatus.Running) {

            printBoard(board.pieceMap())

            var movement = requireMovement()

            if (movement.normal == -1) {
                board.toFile(readln())
                break;
            }

//            while (!board.isValidMovement(movement)) {
//                movement = requireMovement()
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