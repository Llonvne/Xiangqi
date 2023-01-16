package board

import Code.delimiterMap
import Code.fromLightCode
import Code.toLightCode

data class Movement(val from: Point, val to: Point, val normal: Int = 0) : toLightCode<Movement> {

    override fun toLightCode(): String {
        return listOf(from.x, from.y, to.x, to.y, normal).joinToString(delimiterMap.getValue("Movement"))
    }

    companion object : fromLightCode<Movement> {
        override fun fromLightCode(code: String): Movement {
            val nums = code.split(delimiterMap.getValue("Movement")).map { it.toInt() }
            return Movement(Point(nums[0], nums[1]), Point(nums[2], nums[3]), nums[4])
        }
    }
}

