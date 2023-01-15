package pieces

sealed interface PiecePrototypeInterface {
    fun getPieceType(): PieceType

    fun getMovingStrategy(): MovingStrategy

    fun getPieceCode(): Char {
        return typeToCode(this.getPieceType())
    }

    fun getPieceChinese(): String {
        return typeToChinese(this.getPieceType())
    }
}