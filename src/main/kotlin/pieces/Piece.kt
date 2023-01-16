package pieces

import Code.delimiterMap
import Code.fromLightCode
import Code.toLightCode

class Piece(val pType: PieceType, val pColor: PieceColor) : toLightCode<PieceColor> {
    constructor(type: Int, color: Int) : this(
        PieceType.values()[type], PieceColor.values()[color]
    )

    override fun toLightCode(): String {
        return pType.ordinal.toString() + delimiterMap.getValue("Piece") + pColor.ordinal.toString()
    }

    companion object : fromLightCode<Piece> {
        override fun fromLightCode(code: String): Piece {
            val nums = code.split(delimiterMap.getValue("Piece")).map { it.toInt() }
            return Piece(nums[0], nums[1])
        }
    }
}