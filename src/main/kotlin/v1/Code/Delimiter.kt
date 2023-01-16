package v1.Code

val delimiterMap = mapOf<String, String>(
    "Movement" to "#",
    "Point" to "#",
    "Piece" to "#",
    "BetweenRoundAndPieces" to "~",
    "BetweenPointAndPiece" to "!",
    "BetweenPieces" to "$"
)

fun getDelimiter(name: String): String {
    return delimiterMap.getValue(name)
}