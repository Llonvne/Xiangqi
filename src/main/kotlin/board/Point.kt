package board

import Code.delimiterMap
import Code.fromLightCode
import Code.toLightCode

data class Point(var x: Int, var y: Int) : toLightCode<Point> {
    override fun toLightCode(): String {
        return listOf(x, y).joinToString(delimiterMap.getValue("Point"))
    }

    companion object : fromLightCode<Point> {
        override fun fromLightCode(code: String): Point {
            val nums = code.split(delimiterMap.getValue("Point")).map { it.toInt() }
            return Point(nums[0], nums[1])
        }
    }
}
