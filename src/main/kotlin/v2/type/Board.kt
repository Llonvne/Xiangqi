package v2.type

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import v2.prettyJson

@Serializable
class Board(val pieces: MutableList<Piece>) {

    var ignoreMovementRules = true

    fun pointMap() = pieces.associateBy { it.point }

    fun typeMap() = pieces.groupBy { it.type }

    fun colorMap() = pieces.groupBy { it.color }

    fun rawMap() = pieces.groupBy { it.point.x }

    fun colMap() = pieces.groupBy { it.point.y }

    fun positiveMap() = pieces.groupBy { it.point.x + it.point.y }

    fun negativeMap() = pieces.groupBy { it.point.x - it.point.y }

    fun OptionalPoints(piece: Piece): Set<Point> {
        throw NotImplementedError()
    }

    fun OptionalPoints(point: Point): Set<Point> {
        return OptionalPoints(this.pointMap().getValue(point))
    }

    fun applyMove(move: Move): Boolean {
        if (!ignoreMovementRules) {
            if (move.to !in OptionalPoints(move.from)) {
                return false
            }
        }
        this.pointMap().getValue(move.from).point = move.to
        return true
    }

    fun CLIboard() {
        val map = this.pointMap()
        (0..9).forEach { x ->
            (0..8).forEach { y ->
                val res = map[Point(x, y)]
                if (res == null) {
                    print("  ")
                } else {
                    if (res.color == PieceColor.Black) {
                        print(res.typeCode.lowercaseChar())
                    } else {
                        print(res.typeCode)
                    }
                }
            }
            println()
        }
    }

    fun encode(coder: Json = prettyJson): String {
        return coder.encodeToString(this)
    }

    override fun toString(): String {
        return "Board(pieces=$pieces,ignoreMovementRules=$ignoreMovementRules)"
    }

    companion object {
        fun decode(code: String, coder: Json = prettyJson): Board {
            return coder.decodeFromString<Board>(code)
        }
    }
}