package v1.pieces

abstract class PiecePrototype(val pType: PieceType) : PiecePrototypeInterface {
    override fun getPieceType(): PieceType {
        return pType
    }
}