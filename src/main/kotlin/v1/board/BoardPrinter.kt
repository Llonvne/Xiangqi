package v1.board

import v1.pieces.Piece
import v1.pieces.PieceColor
import v1.pieces.typeToCode

fun printBoard(m: Map<Point, Piece>) {
    for (x in 0..9) {
        for (y in 0..8) {
            val res = m[Point(x, y)]
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