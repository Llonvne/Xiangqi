package v1.Code

/**
 * This is the LightCode interface
 * Its purpose is to convert the current board state to a string in order to save the current game
 * He has two functions one is toLightCode which is a sequence of objects
 * The other is fromLightCode which deserializes the object
 * The reason why we use LightCode instead of the common way of serializing objects like JSON is that, as the LightCode name says, we want our LightCode to be as short as possible
 */
interface ToLightCode<T> {
    fun toLightCode(): String
}

interface FromLightCode<T> {
    fun fromLightCode(code: String): T
}