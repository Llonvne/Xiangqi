package pieces.types

import board.Point
import pieces.Piece
import pieces.PiecePrototype
import pieces.PieceType

object Elephant:PiecePrototype(PieceType.Elephant) {
    override fun getAvailablePoint(piece: Piece, point: Point, board: Map<Point, Piece>): Set<Point> {
        TODO("Not yet implemented")
    }
}