package v2

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

val configJsonPath = "./config/xiangqi.json"

val prettyJson = Json { prettyPrint = true;encodeDefaults = true }
val compressedJson = Json { encodeDefaults = true }

@Serializable
class Config() {
    val basePath: String = "./config/"
    val standard: String = basePath + "standard.json"
    val IgnoredMoveRules: Boolean = true
    val savePath: String = "./saves/"

    val standardJson: String by lazy {
        File(Xiangqi2.config.standard).readText()
    }

    fun writeToConfig() {
        File(configJsonPath).writeText(
            prettyJson.encodeToString(this)
        )
    }
}