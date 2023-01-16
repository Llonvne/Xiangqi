package v2.type

data class Move(val from: Point, val to: Point) {
    constructor(fromx: Int, fromy: Int, tox: Int, toy: Int) : this(Point(fromx, fromy), Point(tox, toy))
}
