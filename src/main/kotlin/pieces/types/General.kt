package pieces.types

import board.Point
import pieces.Piece
import pieces.PiecePrototype
import pieces.PieceType

object General : PiecePrototype(PieceType.General) {
    override fun getAvailablePoint(piece: Piece, point: Point, board: Map<Point, Piece>): Set<Point> {
        TODO("Not yet implemented")
    }
}