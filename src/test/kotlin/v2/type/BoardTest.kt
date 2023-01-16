package v2.type

import com.alibaba.fastjson2.to
import com.alibaba.fastjson2.toJSONString
import org.junit.jupiter.api.Test
import v2.type.Piece.Companion.typeToCode
import v2.type.PieceType.*
import kotlin.test.assertEquals

class BoardTest {

    @Test
    fun getPieces() {
        val x = Board(
            mutableListOf(
                Piece(typeToCode(Chariot), 0, 0, 0),
                Piece(typeToCode(Chariot), 0, 0, 8),
                Piece(typeToCode(Chariot), 1, 9, 0),
                Piece(typeToCode(Chariot), 1, 9, 8),
                Piece(typeToCode(Horse), 0, 0, 1),
                Piece(typeToCode(Horse), 0, 0, 7),
                Piece(typeToCode(Horse), 1, 9, 1),
                Piece(typeToCode(Horse), 1, 9, 7),
                Piece(typeToCode(Elephant), 0, 0, 2),
                Piece(typeToCode(Elephant), 0, 0, 6),
                Piece(typeToCode(Elephant), 1, 9, 2),
                Piece(typeToCode(Elephant), 1, 9, 6),
                Piece(typeToCode(Advisor), 0, 0, 3),
                Piece(typeToCode(Advisor), 0, 0, 5),
                Piece(typeToCode(Advisor), 1, 9, 3),
                Piece(typeToCode(Advisor), 1, 9, 5),
                Piece(typeToCode(General), 0, 0, 4),
                Piece(typeToCode(General), 1, 9, 4),
                Piece(typeToCode(Cannon), 0, 2, 1),
                Piece(typeToCode(Cannon), 0, 2, 7),
                Piece(typeToCode(Cannon), 1, 7, 1),
                Piece(typeToCode(Cannon), 1, 7, 7),
            )
        )
        // 兵
        for (i in 0..8 step 2) {
            x.pieces.add(Piece(typeToCode(Soldier), 0, 3, i))
            x.pieces.add(Piece(typeToCode(Soldier), 1, 6, i))
        }

        println(x.toJSONString())
        val x2 = x.toJSONString().to<Board>()
        assertEquals(x.toString(), x2.toString())
        println(x2.toJSONString())
    }
}