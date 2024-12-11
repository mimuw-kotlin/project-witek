package sigma.dataAccess.impl.parsers

import kotlinx.serialization.json.Json
import sigma.dataAccess.impl.data.Configuration
import sigma.dataAccess.model.loggers.ILogger
import sigma.dataAccess.model.parsers.IConfigurationParser
import java.io.File

// TODO("log")
class JsonConfigurationParser(
    private var logger: ILogger
) : IConfigurationParser {
    override fun read(path: String): Configuration {
        val file = File(path)
        if (!file.exists()) {
            throw IllegalArgumentException("Configuration file not found: $path")
        }

        val json = file.readText()
        return try {
            Json.decodeFromString<Configuration>(json)
        } catch (e: Exception) {
            println("Error: ${e.message}")
            throw IllegalArgumentException("Failed to parse configuration: ${e.message}", e)
        }
    }

    override fun write(path: String, configuration: Configuration) {
        TODO("Not yet implemented")
    }
}