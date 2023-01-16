package v2

import v2.type.Board


fun main(args: Array<String>) {
    println("Welcome to the Xiangqi game")
    println("Please select one of the following options to start the game or exit")
    mainSelectionGroup.run()
}

val mainSelectionGroup = SelectionGroup(
    mutableListOf(
        Selection("Start a standard Chinese chess game") {
            val x =Board.decode(Xiangqi2.standardBegin)
            x.CLIboard()
        },
        Selection("Start the game from a specific position (requires a game code)") {
            println("Please enter your game code")
            val code = readln()
        },
        Selection("Start the game from a specific game (requires a game file)") {
            println("Please enter your game file path")
            val path = readln()
        },
        Selection("Quit") {
            println("Have a nice day!")
            return@Selection
        }
    )
)