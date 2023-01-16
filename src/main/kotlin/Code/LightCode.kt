package Code

import board.Board
import board.Point
import board.RoundController
import pieces.Piece
import java.util.*

interface toLightCode<T> {
    fun toLightCode(): String
}

interface fromLightCode<T> {
    fun fromLightCode(code: String): T
}

val delimiterMap = mapOf<String, String>(
    "Movement" to "#",
    "Point" to "#",
    "Piece" to "#",
    "BetweenRoundAndPieces" to "~",
    "BetweenPointAndPiece" to "!",
    "BetweenPieces" to "$"
)

fun getDelimiter(name: String): String {
    return delimiterMap.getValue(name)
}

fun encode(board: Board): String {
    var code = board.roundController.toLightCode() + getDelimiter("BetweenRoundAndPieces")
    var tmp = ""
    for ((point, piece) in board.pieceMap) {
        tmp += point.toLightCode() + getDelimiter("BetweenPointAndPiece") + piece.toLightCode() + getDelimiter("BetweenPieces")
    }
    code += tmp.substring(0..tmp.length - 2)
    return String(Base64.getEncoder().encode(code.toByteArray()));
}

fun decode(initialCode: String): Board {
    val dcode = String(Base64.getDecoder().decode(initialCode))
    val code = dcode.split(getDelimiter("BetweenRoundAndPieces"))
    val round = RoundController.fromLightCode(code[0])
    val pieces = code[1].split(getDelimiter("BetweenPieces"))
    val map = mutableMapOf<Point, Piece>()
    for (piece in pieces) {
        val e = piece.split(getDelimiter("BetweenPointAndPiece"))
        map[Point.fromLightCode(e[0])] = Piece.fromLightCode(e[1])
    }
    return Board(map, round)
}