package board

import Code.decode
import Code.encode
import org.junit.jupiter.api.Test
import pieces.PieceColor
import pieces.PieceType

class BoardTest {

    @Test
    fun toBoardCode() {
        val map = Board(mutableMapOf(), RoundController(PieceColor.Red.ordinal))
        // 添加车
        map.addPiece(PieceType.Chariot, 0, 0, 0)
        map.addPiece(PieceType.Chariot, 0, 0, 8)
        map.addPiece(PieceType.Chariot, 1, 9, 0)
        map.addPiece(PieceType.Chariot, 1, 9, 8)

        // 马
        map.addPiece(PieceType.Horse, 0, 0, 1)
        map.addPiece(PieceType.Horse, 0, 0, 7)
        map.addPiece(PieceType.Horse, 1, 9, 1)
        map.addPiece(PieceType.Horse, 1, 9, 7)

        // 象
        map.addPiece(PieceType.Elephant, 0, 0, 2)
        map.addPiece(PieceType.Elephant, 0, 0, 6)
        map.addPiece(PieceType.Elephant, 1, 9, 2)
        map.addPiece(PieceType.Elephant, 1, 9, 6)

        // 士
        map.addPiece(PieceType.Advisor, 0, 0, 3)
        map.addPiece(PieceType.Advisor, 0, 0, 5)
        map.addPiece(PieceType.Advisor, 1, 9, 3)
        map.addPiece(PieceType.Advisor, 1, 9, 5)

        // 将
        map.addPiece(PieceType.General, 0, 0, 4)
        map.addPiece(PieceType.General, 1, 9, 4)

        // 兵
        for (i in 0..8 step 2) {
            map.addPiece(PieceType.Soldier, 0, 3, i)
            map.addPiece(PieceType.Soldier, 1, 6, i)
        }

        // 炮
        map.addPiece(PieceType.Cannon, 0, 2, 1)
        map.addPiece(PieceType.Cannon, 0, 2, 7)
        map.addPiece(PieceType.Cannon, 1, 7, 1)
        map.addPiece(PieceType.Cannon, 1, 7, 7)

        val x = encode(map)
        println(x)
        val map2 = decode(x)
        println("hello")
    }

    @Test
    fun test_json() {
        println(
        )
    }
}