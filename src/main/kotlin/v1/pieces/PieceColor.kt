package v1.pieces

enum class PieceColor {
    Red, Black
}

fun IndexToColor(index: Int): PieceColor {
    return PieceColor.values()[index]
}