package pieces

import pieces.types.*

enum class PieceType {
    General,
    Advisor,
    Elephant,
    Horse,
    Chariot,
    Cannon,
    Soldier;
}

var PieceTypeToCodeMap = mapOf(
    PieceType.General to 'G',
    PieceType.Advisor to 'A',
    PieceType.Elephant to 'E',
    PieceType.Horse to 'H',
    PieceType.Chariot to 'C',
    PieceType.Cannon to 'N',
    PieceType.Soldier to 'S'
)

val PieceCodeToTypeMap by lazy {
    return@lazy reverseMap(PieceTypeToCodeMap)
}

fun typeToCode(type: PieceType): Char {
    return findUtils(type, PieceTypeToCodeMap)
}

fun codeToType(code: Char): PieceType {
    return findUtils(code, PieceCodeToTypeMap)
}

var TypeToPrototypeMap = mapOf(
    PieceType.General to General,
    PieceType.Advisor to Advisor,
    PieceType.Elephant to Elephant,
    PieceType.Horse to Horse,
    PieceType.Chariot to Chariot,
    PieceType.Cannon to Cannon,
    PieceType.Soldier to Soldier
)

fun typeToProto(type: PieceType): PiecePrototype {
    return findUtils(type, TypeToPrototypeMap)
}

fun <I, O> findUtils(key: I, where: Map<I, O>): O {
    if (key == null) {
        throw Exception("findUtils 中 key 为 null")
    }
    return where[key] ?: throw Exception("PieceType: ${key!!::class.simpleName} 未在Map被定义.")
}

fun <K, V> reverseMap(map: Map<K, V>): MutableMap<V, K> {
    val ret = mutableMapOf<V, K>()
    for ((key, value) in map) {
        ret[value] = key
    }
    return ret
}