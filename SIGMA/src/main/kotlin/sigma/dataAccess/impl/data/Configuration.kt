package sigma.dataAccess.impl.data

import kotlinx.serialization.Serializable

@Serializable
data class Configuration(
    var resolutions: MutableList<Resolution>,
    val timelinePath: String,
    val completionStatusWeights: CompletionStatusWeights? = null,
    val dayColors: DayColors? = null
) {
    companion object {
        fun getDefault(): Configuration {
            return Configuration(mutableListOf(), "C:\\Program Files\\Sigma\\timeline.csv")
        }
    }

    fun update(resolutions: MutableList<Resolution>) {
        this.resolutions = resolutions
    }
}

@Serializable
data class CompletionStatusWeights(
    val completed: Double,
    val partial: Double,
    val uncompleted: Double,
    val unknown: Double
)

@Serializable
data class DayColors(
    val success: String,
    val failure: String,
    val empty: String,
    val future: String
)
