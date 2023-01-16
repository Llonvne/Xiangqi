package v2.type

import com.alibaba.fastjson2.to
import com.alibaba.fastjson2.toJSONString
import org.junit.jupiter.api.Test

class PieceTest {

    @Test
    fun getPieceType() {
        println(Piece('C'.code, 0, 0, 0).toJSONString())
        val x = Piece('C'.code, 0, 0, 0).toJSONString()
        println(x.to<Piece>().toString())
    }
}