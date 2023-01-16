package v2.type

import kotlinx.serialization.Serializable

@Serializable
class Board(val pieces: MutableList<Piece>) {
    override fun toString(): String {
        return "Board(pieces=$pieces)"
    }
}