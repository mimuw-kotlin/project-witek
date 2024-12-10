package sigma.dataAccess.impl.data

import kotlinx.serialization.Serializable

@Serializable
data class Configuration(
    val resolutions: Resolutions,
    val timeline: TimelineItem,
    val completionStatus: CompletionStatusItem?
)

@Serializable
data class File(
    val path: String
)

@Serializable
data class Resolutions(
    val file: File,
    val list: List<ResolutionItem>
)

@Serializable
data class ResolutionItem(
    val name: String,
    val description: String,
    val image: String
)

@Serializable
data class TimelineItem(
    val file: File
)

@Serializable
data class CompletionStatusItem(
    val weights: Weights
)

@Serializable
data class Weights(
    val completed: Double,
    val partial: Double,
    val uncompleted: Double,
    val unknown: Double
)
