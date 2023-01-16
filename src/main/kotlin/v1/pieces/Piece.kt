package v1.pieces

import v1.Code.FromLightCode
import v1.Code.ToLightCode
import v1.Code.delimiterMap

class Piece(val pType: PieceType, val pColor: PieceColor) : ToLightCode<PieceColor> {
    constructor(type: Int, color: Int) : this(
        PieceType.values()[type], PieceColor.values()[color]
    )

    override fun toLightCode(): String {
        return pType.ordinal.toString() + delimiterMap.getValue("Piece") + pColor.ordinal.toString()
    }

    companion object : FromLightCode<Piece> {
        override fun fromLightCode(code: String): Piece {
            val nums = code.split(delimiterMap.getValue("Piece")).map { it.toInt() }
            return Piece(nums[0], nums[1])
        }
    }
}