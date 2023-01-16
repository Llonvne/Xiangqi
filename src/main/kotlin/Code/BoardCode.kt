package Code

import board.Board
import board.Point
import board.RoundController
import pieces.Piece

/**
 * This is the BoardCode file, which converts a Board object into a LightCode,
 * note that a Board object only contains the current game and who is playing now,
 * it does not contain the game history,
 * if you need a game with a history, please use FileCode
 */

fun encode(board: Board): String {
    var code = board.roundController.toLightCode() + getDelimiter("BetweenRoundAndPieces")
    var tmp = ""
    for ((point, piece) in board.pieceMap) {
        tmp += point.toLightCode() + getDelimiter("BetweenPointAndPiece") + piece.toLightCode() + getDelimiter("BetweenPieces")
    }
    code += tmp.substring(0..tmp.length - 2)
    return code
}

fun decode(initialCode: String): Board {
    val code = initialCode.split(getDelimiter("BetweenRoundAndPieces"))
    val round = RoundController.fromLightCode(code[0])
    val pieces = code[1].split(getDelimiter("BetweenPieces"))
    val map = mutableMapOf<Point, Piece>()
    for (piece in pieces) {
        val e = piece.split(getDelimiter("BetweenPointAndPiece"))
        map[Point.fromLightCode(e[0])] = Piece.fromLightCode(e[1])
    }
    return Board(map, round)
}