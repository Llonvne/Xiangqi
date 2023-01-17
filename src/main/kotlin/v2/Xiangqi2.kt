package v2

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

class Xiangqi2 {
    companion object {
        private var json = Json { prettyPrint = true;encodeDefaults = true }

        val config: Config
            get() {
                while (true) {
                    try {
                        return Json.decodeFromString<Config>(File("./config/xiangqi.json").readText())
                    } catch (e: Exception) {
                        println("Your xiangqi.json (game configuration file) is not in the correct format or does not exist, the default configuration file is being restored for you")
                        Config().writeToConfig()
                    }
                }
            }
    }
}