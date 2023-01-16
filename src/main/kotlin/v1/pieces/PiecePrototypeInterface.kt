package v1.pieces

import v1.board.Point

sealed interface PiecePrototypeInterface {
    fun getPieceType(): PieceType

    fun getPieceCode(): Char {
        return typeToCode(this.getPieceType())
    }

    fun getPieceChinese(): String {
        return typeToChinese(this.getPieceType())
    }

    fun getAvailablePoint(piece: Piece, point: Point, board: Map<Point, Piece>): Set<Point>
}