package board

import pieces.Piece
import pieces.Piece.Companion.fromPieceCode
import pieces.PieceColor
import pieces.PieceType
import pieces.typeToCode
import java.util.*

class BoardPieceMap(initialCode: String = emptyBoardCode, base64Encode: Boolean = false) {

    val boardPieceMap = mutableMapOf<Point, Piece>()

    init {
        var code = initialCode
        if (code != emptyBoardCode) {

            if (base64Encode) {
                code = String(Base64.getDecoder().decode(code))
            }

            val piecesCode = code.split(pieceSpliterator)
            for (pieceCode in piecesCode) {

                val pointAndType = pieceCode.split(pointTypeSpliterator)
                val point = Point(pointAndType[0])
                val piece = fromPieceCode(pointAndType[1])
                boardPieceMap[point] = piece
            }
        }
    }

    fun addPiece(type: PieceType, color: PieceColor, x: Int, y: Int) {
        this.boardPieceMap[Point(x, y)] = Piece(type, color)
    }

    fun addPiece(type: PieceType, color: Int, x: Int, y: Int) {
        addPiece(type, PieceColor.values()[color], x, y)
    }

    fun getBoardCode(): String {
        var res = ""
        for ((point, piece) in boardPieceMap) {
            res += point.toString() + pointTypeSpliterator + piece.toString() + pieceSpliterator
        }

        // 如果不为空
        if (res != "") {
            return res.substring(0, res.length - 1)
        } else {
            return emptyBoardCode
        }
    }

    fun getBase64EncodeBoardCode(): String {
        return Base64.getEncoder().encodeToString(
            getBoardCode().encodeToByteArray()
        )
    }

    fun printBoard() {
        for (x in 0..9) {
            for (y in 0..8) {
                val res = boardPieceMap[Point(x, y)]
                if (res == null) {
                    print(" ")
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

        // BoardCode
        val emptyBoardCode = "##EMPTY##"
        val standardCode =
            "MCwwfEMjUmVkJjAsOHxDI1JlZCY5LDB8QyNCbGFjayY5LDh8QyNCbGFjayYwLDF8SCNSZWQmMCw3fEgjUmVkJjksMXxII0JsYWNrJjksN3xII0JsYWNrJjAsMnxFI1JlZCYwLDZ8RSNSZWQmOSwyfEUjQmxhY2smOSw2fEUjQmxhY2smMCwzfEEjUmVkJjAsNXxBI1JlZCY5LDN8QSNCbGFjayY5LDV8QSNCbGFjayYwLDR8RyNSZWQmOSw0fEcjQmxhY2smMywwfFMjUmVkJjYsMHxTI0JsYWNrJjMsMnxTI1JlZCY2LDJ8UyNCbGFjayYzLDR8UyNSZWQmNiw0fFMjQmxhY2smMyw2fFMjUmVkJjYsNnxTI0JsYWNrJjMsOHxTI1JlZCY2LDh8UyNCbGFjayYyLDF8TiNSZWQmMiw3fE4jUmVkJjcsMXxOI0JsYWNrJjcsN3xOI0JsYWNr"
    }
}