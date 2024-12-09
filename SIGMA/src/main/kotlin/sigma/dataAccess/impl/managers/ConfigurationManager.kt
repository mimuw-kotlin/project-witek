package sigma.dataAccess.impl.managers

import sigma.dataAccess.impl.data.Configuration

class ConfigurationManager {
    private var configuration: Configuration? = null

    fun getConfiguration(): Configuration {
        TODO()
    }

    fun getTimelinePath(): String {
        return "C:\\Program Files\\Sigma\\timeline.csv"
    }

    fun getResolutionsPath(): String {
        return "C:\\Program Files\\Sigma\\resolutions.csv"
    }
}