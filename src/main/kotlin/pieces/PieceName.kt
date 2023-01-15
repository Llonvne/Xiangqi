package pieces

val PieceChineseNameMap = mapOf(
    PieceType.General to "将",
    PieceType.Advisor to "士",
    PieceType.Elephant to "象",
    PieceType.Horse to "马",
    PieceType.Chariot to "车",
    PieceType.Cannon to "炮",
    PieceType.Soldier to "卒"
)

fun typeToChinese(type: PieceType):String{
    val result = PieceChineseNameMap[type]
    if (result == null){
        throw Exception("PieceType: ${type.name} 未在 PieceChineseNameMap 中被定义.")
    }
    else {
        return result
    }
}