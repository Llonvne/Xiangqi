package board

data class Point(var x: Int, var y: Int) {

    constructor(pointCode: String) : this(0, 0) {
        val xAndYStrings = pointCode.split(xAndYSpliterator)
        this.x = xAndYStrings[0].toInt()
        this.y = xAndYStrings[1].toInt()
    }

    override fun toString(): String {
        return "$x$xAndYSpliterator$y"
    }

    companion object {
        const val xAndYSpliterator = ','
    }
}
