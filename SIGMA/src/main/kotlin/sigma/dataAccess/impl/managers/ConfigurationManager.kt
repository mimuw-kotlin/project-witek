package sigma.dataAccess.impl.managers

import sigma.dataAccess.impl.data.Configuration

import java.io.File
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString
import sigma.dataAccess.impl.data.Resolution

class ConfigurationManager {
    private var configuration: Configuration

    init {
        configuration = parseConfiguration("C:\\Program Files\\Sigma\\appsettings.json")
    }

    private fun parseConfiguration(filePath: String): Configuration {
        val file = File(filePath)
        if (!file.exists()) {
            throw IllegalArgumentException("Configuration file not found: $filePath")
        }

        val json = file.readText()
        return try {
            Json.decodeFromString<Configuration>(json)
        } catch (e: Exception) {
            println("Error: ${e.message}")
            throw IllegalArgumentException("Failed to parse configuration: ${e.message}", e)
        }
    }

    fun getConfiguration(): Configuration {
        TODO()
    }

    fun getTimelinePath(): String {
        return configuration.timeline.file.path
    }

    fun getResolutionsPath(): String {
        return configuration.resolutions.file.path
    }

    fun getResolutionsList(): List<Resolution> {
        return configuration.resolutions.list.map { resolutionItem ->
            Resolution(
                name = resolutionItem.name,
                description = resolutionItem.description,
                image = resolutionItem.image
            )
        }
    }

}