package pieces

abstract class PiecePrototype(val pType: PieceType) : PiecePrototypeInterface {
    override fun getPieceType(): PieceType {
        return pType
    }

    override fun getMovingStrategy(): MovingStrategy {
        return typeToStrategy(getPieceType())
    }
}