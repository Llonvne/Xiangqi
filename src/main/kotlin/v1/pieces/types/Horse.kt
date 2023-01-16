package v1.pieces.types

import v1.board.Point
import v1.pieces.Piece
import v1.pieces.PiecePrototype
import v1.pieces.PieceType

object Horse : PiecePrototype(PieceType.Horse) {
    override fun getAvailablePoint(piece: Piece, point: Point, board: Map<Point, Piece>): Set<Point> {
        TODO("Not yet implemented")
    }
}