package board

import pieces.*

class Board(val pieceMap: MutableMap<Point, Piece>, val roundController: RoundController) {

    fun isValidMovement(movement: Movement): Boolean {
        // 检查 from 位置是否有棋子,如果没有就是不合法的
        val fromPiece = this.pieceMap[movement.from] ?: return false
        // 检查 to 位置是否是否有棋子，如果有先检查颜色，颜色相同，一定不合理，否则检查能否到达
        val toPiece = this.pieceMap[movement.to]
        // 如果有棋子
        if (toPiece != null && toPiece.pColor == roundController.now()) {
            return false
        }
        return movement.to in getAvailablePoint(fromPiece, movement.from)
    }

    fun applyMovement(movement: Movement) {
        val piece = this.pieceMap[movement.from] ?: throw Exception("正在尝试应用一个不合法的 Movement...")
        this.pieceMap.remove(movement.from)
        this.pieceMap[movement.to] = piece

        roundController.next()
    }

    private fun getAvailablePoint(piece: Piece, point: Point): Set<Point> {
        val prototype = typeToProto(piece.pType)
        return prototype.getAvailablePoint(piece, point, pieceMap)
    }

    fun addPiece(type: PieceType, color: PieceColor, x: Int, y: Int) {
        this.pieceMap[Point(x, y)] = Piece(type, color)
    }

    fun addPiece(type: PieceType, color: Int, x: Int, y: Int) {
        addPiece(type, IndexToColor(color), x, y)
    }
}