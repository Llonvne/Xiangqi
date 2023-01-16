package board

import pieces.Piece

interface AbstractBoard {
    fun getGameStatus(): GameStatus

    fun isValidMovement(movement: Movement): Boolean

    fun applyMovement(movement: Movement)

    fun getAvailablePoint(piece: Piece, point: Point): Set<Point>

    fun pieceMap(): Map<Point, Piece>
}