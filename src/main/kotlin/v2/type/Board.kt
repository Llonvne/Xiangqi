package v2.type

class Board(val pieces: MutableList<Piece>) {
    override fun toString(): String {
        return "Board(pieces=$pieces)"
    }
}