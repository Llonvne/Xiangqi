import v1.Selection
import v1.SelectionGroup
import v1.Xiangqi
import v1.standardCode

fun main(args: Array<String>) {
    println("Welcome to the v1.Xiangqi game")
    println("Please select one of the following options to start the game or exit")
    mainSelectionGroup.run()
}

val mainSelectionGroup = SelectionGroup(
    mutableListOf(
        Selection("Start a standard Chinese chess game") {
            Xiangqi().beginFromCode(standardCode)
        },
        Selection("Start the game from a specific position (requires a game code)") {
            println("Please enter your game code")
            val code = readln()
            Xiangqi().beginFromCode(code)
        },
        Selection("Start the game from a specific game (requires a game file)") {
            println("Please enter your game file path")
            val path = readln()
            Xiangqi().beginFromFile(path)
        },
        Selection("Quit") {
            println("Have a nice day!")
            return@Selection
        }
    )
)