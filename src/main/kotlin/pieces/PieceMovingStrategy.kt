package pieces

interface MovingStrategy {
}

val PieceMovingStrategyMap = mapOf<PieceType, MovingStrategy>()

fun typeToStrategy(type: PieceType): MovingStrategy {
    val result = PieceMovingStrategyMap[type]
    if (result == null){
        throw Exception("PieceType: ${type.name} 未在 PieceMovingStrategyMap 中被定义.")
    }
    else {
        return result
    }
}