package board

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MovementTest {

    @Test
    fun toLightCode() {
        assertEquals("1#0#2#0#0", Movement(Point(1, 0), Point(2, 0), 0).toLightCode())
    }

    @Test
    fun fromLightCode() {
        assertEquals(Movement.fromLightCode("1#0#2#0#0"),Movement(Point(1, 0), Point(2, 0), 0))
    }
}