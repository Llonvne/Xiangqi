package v2.type

import kotlinx.serialization.Serializable

/**
 * This is the definition of a chess piece
 * It contains the type of the piece, the color of the piece, and its coordinates
 * It contains a serialization notation
 */
@Serializable
data class Piece(val type: PieceType, val color: PieceColor, var point: Point) {
    constructor(type: PieceType, colorIndex: Int, x: Int, y: Int) : this(type, PieceColor.values()[colorIndex], x, y)

    constructor(type: PieceType, color: PieceColor, x: Int, y: Int) : this(type, color, Point(x, y))

    val typeCode = _TypeToCodeMap.getValue(type)

}

@Serializable
data class Point(var x: Int, var y: Int) {

}

enum class PieceColor {
    Red, Black
}

enum class PieceType {
    General,
    Advisor,
    Elephant,
    Horse,
    Chariot,
    Cannon,
    Soldier;
}

private val _PieceChineseNameMap = mapOf(
    PieceType.General to "将",
    PieceType.Advisor to "士",
    PieceType.Elephant to "象",
    PieceType.Horse to "马",
    PieceType.Chariot to "车",
    PieceType.Cannon to "炮",
    PieceType.Soldier to "卒"
)

private val _TypeToCodeMap = mapOf(
    PieceType.General to 'G',
    PieceType.Advisor to 'A',
    PieceType.Elephant to 'E',
    PieceType.Horse to 'H',
    PieceType.Chariot to 'C',
    PieceType.Cannon to 'N',
    PieceType.Soldier to 'S'
)

private val _PieceCodeToTypeMap by lazy {
    return@lazy reverseMap(_TypeToCodeMap)
}

private fun <K, V> reverseMap(map: Map<K, V>): MutableMap<V, K> {
    val ret = mutableMapOf<V, K>()
    for ((key, value) in map) {
        ret[value] = key
    }
    return ret
}