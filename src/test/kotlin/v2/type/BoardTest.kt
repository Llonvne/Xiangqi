package v2.type

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import v2.type.PieceType.*
import java.io.File

class BoardTest {

    @Test
    fun getPieces() {
        val x = Board(
            mutableListOf(
                Piece(Chariot, 0, 0, 0),
                Piece(Chariot, 0, 0, 8),
                Piece(Chariot, 1, 9, 0),
                Piece(Chariot, 1, 9, 8),
                Piece(Horse, 0, 0, 1),
                Piece(Horse, 0, 0, 7),
                Piece(Horse, 1, 9, 1),
                Piece(Horse, 1, 9, 7),
                Piece(Elephant, 0, 0, 2),
                Piece(Elephant, 0, 0, 6),
                Piece(Elephant, 1, 9, 2),
                Piece(Elephant, 1, 9, 6),
                Piece(Advisor, 0, 0, 3),
                Piece(Advisor, 0, 0, 5),
                Piece(Advisor, 1, 9, 3),
                Piece(Advisor, 1, 9, 5),
                Piece(General, 0, 0, 4),
                Piece(General, 1, 9, 4),
                Piece(Cannon, 0, 2, 1),
                Piece(Cannon, 0, 2, 7),
                Piece(Cannon, 1, 7, 1),
                Piece(Cannon, 1, 7, 7),
            )
        )
        // 兵
        for (i in 0..8 step 2) {
            x.pieces.add(Piece(Soldier, 0, 3, i))
            x.pieces.add(Piece(Soldier, 1, 6, i))
        }

        var format = Json {prettyPrint = true;encodeDefaults = true}

        // Serializing objects
        val string = format.encodeToString(x)
        println(string)
        val obj = format.decodeFromString<Board>(string)
        println(obj) // Project(name=kotlinx.serialization, language=Kotlin)

        val f = File("1.json")
        f.createNewFile()
        f.writeText(string)
    }
}