import board.Board
import board.Movement
import board.Point
import java.io.File
import java.util.*

class Xiangqi {

    var code = mutableListOf<String>()

    private fun requireChoices(vararg choice: String): String {
        var input = readlnOrNull()
        val choiceSet = choice.toSet();
        while (input == null || input !in choiceSet) {
            println("您输入的并不正确，请重新输入...")
            input = readlnOrNull()
        }
        return input
    }

    private fun getMovement(): Movement {
        while (true) {
            val line = readln().trim()
            val split = line.split(" ")

            if (split.size == 1) {
                return Movement(Point(0, 0), Point(0, 0), split[0].toInt())
            }

            if (split.size != 4) {
                println("Your input format doesn't make sense")
                continue
            }
            val e = try {
                split.map { it.toInt() }
            } catch (e: Exception) {
                println("Your input format doesn't make sense")
                continue
            }
            return Movement(Point(e[0], e[1]), Point(e[2], e[3]))
        }
    }

    private fun saveRecords() {
        val f = File("${Date().time}_record.txt")
        f.createNewFile()
        f.printWriter().use {
            for (record in code) {
                it.println(record)
            }
        }
    }

    fun start() {
        println("Xiangqi CLI")
        println("Game is starting ...")

        var choice: Int = -1
        while (true) {
            println("---------------------------")
            println("Select your game mode:")
            println("1. Start a standard Chinese chess game")
            println("2. Start with the game of the given game code (need to enter the game code)");
            println("0. Exit")

            try {
                choice = requireChoices("1", "2", "0").toInt()
                when (choice) {
                    0 -> return
                    1 -> begin(Board.standardCode)
                    2 -> {
                        println("Please enter your game code")
                        var code = readlnOrNull()
                        while (code == null) {
                            code = readlnOrNull()
                        }

                        try {
                            begin(code)
                        } catch (e: GameCodeException) {
                            println(e.message)
                        }
                    }
                }
            } catch (e: GameException) {
                println(e.message)
            } finally {
                if (choice == 1 || choice == 2) {
                    saveRecords();
                }
            }
        }
    }

    class GameCodeException : Exception("Your game code cannot be parsed, please check if it is correct") {
    }

    class GameException(code: String) :
        Exception("Your game crashed for some reason, this may be due to incorrect input or game bugs, but we have saved the most recent normal game code, you can revert to the game directly from the game code\n$code")

    fun begin(initialCode: String) {
        code.clear()
        val board = try {
            // 使用获得的棋盘代码初始化棋盘
            Board(initialCode, true)
        } catch (e: Exception) {
            throw GameCodeException()
        }

        println(
            "The movement format is X Y x y\n" +
                    "(Upper case letters are the starting position, lower case are the ending position)"
        )
        println(
            "If you want to pause the game please enter -1, you will get a code, " +
                    "please save it and enter it when you start the next game to restore the current state"
        )
        try {
            while (board.checkStatus() == Board.GameStatus.Running) {

                code.add(board.getBase64EncodeBoardCode())

                // 打印当前棋盘
                board.printBoard()

                println("Now it's ${board.nowColor}'s turn to move")

                // 获得一个合法格式的 Movement (该处合法为有四个数字即可)
                var movement = getMovement()

                // 检查normal判断是否是 normal 或者是 其他要求
                if (movement.normal == -1) {
                    println("Your game save code will be shown in the next line, please save it")
                    println(board.getBase64EncodeBoardCode())
                    return
                }

                if (!IgnoreMovementLegalizationCheck) {
                    // 检查改 Movement 是否在 board 中成立
                    while (!board.isValidMovement(movement)) {
                        movement = getMovement()
                    }
                }

                // 成立就应用
                board.applyMovement(movement)
            }
        } catch (e: Exception) {
            throw GameException(code.last())
        }
    }

    fun showCodeStack(records: List<String>) {
        println("We also record the game code for each move you make, and we can export it to \$time\$record.txt if you need it.")
        if (requireChoices("0", "1") == "1") {
            for (record in records) {
                println(record)
            }
        }
    }

    companion object {
        const val IgnoreMovementLegalizationCheck = true
    }
}