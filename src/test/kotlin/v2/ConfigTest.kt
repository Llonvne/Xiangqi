package v2

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test

class ConfigTest{
    @Test
    fun t1(){
        val c = Config()
        val j = Json {prettyPrint = true;encodeDefaults = true}
        println(j.encodeToString(c))
    }
}