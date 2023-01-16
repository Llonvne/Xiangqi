package v2.type

import kotlinx.serialization.Serializable

/**
 * This is the definition of a chess piece
 * It contains the type code of the pawn, the color code of the pawn, and its coordinates
 * The purpose of using type codes and color codes is to make it easier to interact with JSON,
 * but in the actual coding process we want to use enumerated types for more security,
 * so we also provide methods to get the corresponding color enumerated classes and type enumerated classes
 *
 * We are trying to move to Kotlin's native serialization approach instead of using FastJson
 * to solve many of the difficulties of dealing with conversion literals.
 */
@Serializable
data class Piece(val typeCode: Int, val colorIndex: Int, var x: Int, var y: Int) {
    /**
     * You may have noticed that we are storing Int values for Char
     * because we found that the FastJson library would not parse private Char variables properly,
     * so we converted them to Int, but we still provide a constructor for Char via the helper constructor
     * You can ignore the internal implementation and use the Char constructor as you wish
     */
    constructor(code: Char, color: Int, x: Int, y: Int) : this(code.code, color, x, y) {}

    /**
     * We found that we could not provide a type-based constructor in the constructor,
     * and once we did, FastJson would not store the TypeCode in the Json properly,
     * we guessed that FastJson did not handle enumerated types and shaping properly,
     * so we used the wrong constructor, but we still might need a type-based constructor.
     * so we moved it to the companion object
     */
    constructor(type: PieceType, color: Int, x: Int, y: Int) : this(typeToCode(type), color, x, y)

    fun GetPieceType(): PieceType {
        return _PieceCodeToTypeMap.getValue(typeCode.toChar())
    }

    fun getColor(): PieceColor {
        return PieceColor.values()[colorIndex]
    }

    companion object {
        fun typeToCode(type: PieceType): Char {
            return _TypeToCodeMap.getValue(type)
        }

        fun colorToIndex(color: PieceColor): Int {
            return color.ordinal
        }
    }

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

private val PieceChineseNameMap = mapOf(
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