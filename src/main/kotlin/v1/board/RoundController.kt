package v1.board

import v1.Code.FromLightCode
import v1.Code.ToLightCode
import v1.pieces.PieceColor

class RoundController(var index: Int) : ToLightCode<RoundController> {

    constructor(color: PieceColor) : this(color.ordinal)

    fun now(): PieceColor {
        return PieceColor.values()[index]
    }

    fun next(): PieceColor {
        index = 1 - index
        return now();
    }

    override fun toLightCode(): String {
        return index.toString()
    }

    companion object : FromLightCode<RoundController> {
        override fun fromLightCode(code: String): RoundController {
            return RoundController(code.toInt())
        }
    }
}