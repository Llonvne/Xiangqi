package board

import pieces.*
import pieces.Piece.Companion.fromPieceCode
import java.util.*

class Board(initialCode: String = emptyBoardCode, base64Encode: Boolean = false) {

    enum class GameStatus {
        Red, Black, Running
    }

    class TurnCount {
        private var turn = firstTurn
        fun next(): PieceColor {
            return PieceColor.values()[turn].also { turn = 1 - turn }
        }

        fun now(): PieceColor {
            return PieceColor.values()[turn]
        }

        companion object {
            const val firstTurn = 0
        }
    }

    val turn = TurnCount()

    /**
     * 该 map 记录了每一个棋子的位置到其棋子信息
     */
    private val boardPieceMap = mutableMapOf<Point, Piece>()

    val nowColor: PieceColor get() = turn.now()

    init {
        var code = initialCode
        if (code != emptyBoardCode) {

            if (base64Encode) {
                code = String(Base64.getDecoder().decode(code))
            }

            val piecesCodeAndNowColor = code.split(nowColorSpliterator)

            val color = PieceColor.valueOf(piecesCodeAndNowColor[1])
            if (color != nowColor) {
                turn.next();
            }

            val piecesCode = piecesCodeAndNowColor[0].split(pieceSpliterator)
            for (pieceCode in piecesCode) {

                val pointAndType = pieceCode.split(pointTypeSpliterator)
                val point = Point(pointAndType[0])
                val piece = fromPieceCode(pointAndType[1])
                boardPieceMap[point] = piece
            }
        }
    }

    fun isValidMovement(movement: Movement): Boolean {
        // 检查 from 位置是否有棋子,如果没有就是不合法的
        val fromPiece = this.boardPieceMap[movement.from] ?: return false
        // 检查 to 位置是否是否有棋子，如果有先检查颜色，颜色相同，一定不合理，否则检查能否到达
        val toPiece = this.boardPieceMap[movement.to]
        // 如果有棋子
        if (toPiece != null && toPiece.pColor == nowColor) {
            return false
        }
        return movement.to in getAvailablePoint(fromPiece, movement.from)
    }

    fun applyMovement(movement: Movement) {
        val piece = this.boardPieceMap[movement.from] ?: throw Exception("正在尝试应用一个不合法的 Movement...")
        this.boardPieceMap.remove(movement.from)
        this.boardPieceMap[movement.to] = piece

        turn.next()
    }

    fun checkStatus(): GameStatus {
        return GameStatus.Running
    }

    private fun getAvailablePoint(piece: Piece, point: Point): Set<Point> {
        val prototype = typeToProto(piece.pType)
        return prototype.getAvailablePoint(piece, point, boardPieceMap)
    }

    fun addPiece(type: PieceType, color: PieceColor, x: Int, y: Int) {
        this.boardPieceMap[Point(x, y)] = Piece(type, color)
    }

    fun addPiece(type: PieceType, color: Int, x: Int, y: Int) {
        addPiece(type, PieceColor.values()[color], x, y)
    }

    private fun getBoardCode(nowColor: PieceColor): String {
        var res = ""
        for ((point, piece) in boardPieceMap) {
            res += point.toString() + pointTypeSpliterator + piece.toString() + pieceSpliterator
        }

        // 如果不为空
        if (res != "") {
            return res.substring(0, res.length - 1) + nowColorSpliterator + nowColor
        } else {
            return emptyBoardCode
        }
    }

    fun getBase64EncodeBoardCode(): String {
        return Base64.getEncoder().encodeToString(
            getBoardCode(nowColor).encodeToByteArray()
        )
    }

    fun printBoard() {
        for (x in 0..9) {
            for (y in 0..8) {
                val res = boardPieceMap[Point(x, y)]
                if (res == null) {
                    print("  ")
                } else {
                    if (res.pColor == PieceColor.Black) {
                        print(typeToCode(res.pType).lowercaseChar())
                    } else {
                        print(typeToCode(res.pType))
                    }
                }
            }
            println()
        }
    }

    companion object {

        // Spliterator
        val pieceSpliterator = "&"
        val pointTypeSpliterator = "|"
        val nowColorSpliterator = "-"

        // BoardCode
        val emptyBoardCode = "##EMPTY##"
        val standardCode =
            "MCwwfEMjUmVkJjAsOHxDI1JlZCY5LDB8QyNCbGFjayY5LDh8QyNCbGFjayYwLDF8SCNSZWQmMCw3fEgjUmVkJjksMXxII0JsYWNrJjksN3xII0JsYWNrJjAsMnxFI1JlZCYwLDZ8RSNSZWQmOSwyfEUjQmxhY2smOSw2fEUjQmxhY2smMCwzfEEjUmVkJjAsNXxBI1JlZCY5LDN8QSNCbGFjayY5LDV8QSNCbGFjayYwLDR8RyNSZWQmOSw0fEcjQmxhY2smMywwfFMjUmVkJjYsMHxTI0JsYWNrJjMsMnxTI1JlZCY2LDJ8UyNCbGFjayYzLDR8UyNSZWQmNiw0fFMjQmxhY2smMyw2fFMjUmVkJjYsNnxTI0JsYWNrJjMsOHxTI1JlZCY2LDh8UyNCbGFjayYyLDF8TiNSZWQmMiw3fE4jUmVkJjcsMXxOI0JsYWNrJjcsN3xOI0JsYWNrLVJlZA=="
    }
}