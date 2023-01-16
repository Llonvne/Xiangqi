package board

import Code.fromLightCode
import Code.toLightCode
import pieces.PieceColor

class RoundController(var index: Int) : toLightCode<RoundController> {

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

    companion object : fromLightCode<RoundController> {
        override fun fromLightCode(code: String): RoundController {
            return RoundController(code.toInt())
        }
    }
}