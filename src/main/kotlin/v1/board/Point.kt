package v1.board

import v1.Code.FromLightCode
import v1.Code.ToLightCode
import v1.Code.delimiterMap

data class Point(var x: Int, var y: Int) : ToLightCode<Point> {
    override fun toLightCode(): String {
        return listOf(x, y).joinToString(delimiterMap.getValue("Point"))
    }

    companion object : FromLightCode<Point> {
        override fun fromLightCode(code: String): Point {
            val nums = code.split(delimiterMap.getValue("Point")).map { it.toInt() }
            return Point(nums[0], nums[1])
        }
    }
}
