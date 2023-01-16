package v1.board

import v1.pieces.Piece

interface AbstractBoard {
    fun getGameStatus(): GameStatus

    fun isValidMovement(movement: Movement): Boolean

    fun applyMovement(movement: Movement)

    fun reverseMovement(movement: Movement) {
        this.applyMovement(Movement(movement.to, movement.from, movement.normal))
    }

    fun getAvailablePoint(piece: Piece, point: Point): Set<Point>

    fun pieceMap(): Map<Point, Piece>
}