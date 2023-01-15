package pieces

class Piece(val pType: PieceType, val pColor: PieceColor) {

    override fun toString(): String {
        return "${typeToCode(pType)}$typeAndColorSpliterator$pColor"
    }

    companion object {
        const val typeAndColorSpliterator = "#"
        fun fromPieceCode(code: String): Piece {
            val typeAndColor = code.split(typeAndColorSpliterator)
            val type = codeToType(typeAndColor[0][0])
            val color = PieceColor.valueOf(typeAndColor[1])
            return Piece(type, color)
        }
    }
}