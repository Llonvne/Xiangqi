package pieces.types

import board.Point
import pieces.Piece
import pieces.PiecePrototype
import pieces.PieceType

object Horse : PiecePrototype(PieceType.Horse) {
    override fun getAvailablePoint(piece: Piece, point: Point, board: Map<Point, Piece>): Set<Point> {
        TODO("Not yet implemented")
    }
}