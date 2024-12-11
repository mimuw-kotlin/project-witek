package sigma.businessLogic.impl.managers

import sigma.dataAccess.impl.data.CompletionStatus
import sigma.dataAccess.impl.data.Configuration
import sigma.dataAccess.impl.data.Resolution
import sigma.dataAccess.impl.data.Timeline
import sigma.dataAccess.model.loggers.ILogger
import sigma.dataAccess.model.parsers.IConfigurationParser
import sigma.dataAccess.model.parsers.ITimelineParser

// TODO("Refactor - move some responsibility to other classes???")
class ResolutionsManager(
    private val logger: ILogger,
    private val configurationParser: IConfigurationParser,
    private val timelineParser: ITimelineParser
) {
    private var configurationWritePath : String = "C:\\Program Files\\Sigma\\appsettings.json"
    private var configurationReadPath : String = configurationWritePath
    private var configuration: Configuration = Configuration.getDefault()
    private var timeline: Timeline = Timeline.getDefault()

    fun tryInit() : Unit {
            configuration = configurationParser.read(configurationReadPath)
            logger.debug("Successfully parsed configuration.")
            timeline = timelineParser.read(configuration.timelinePath, configuration.resolutions.size)
            logger.debug("Successfully parsed timeline.")
    }

    fun close() : Unit {
        configurationParser.write(configurationWritePath, configuration)
        timelineParser.write(configuration.timelinePath, timeline)
    }

    fun setConfigurationReadPath(path: String) {
        configurationReadPath = path
    }


    fun addResolution(resolution: Resolution): Unit {
        // validate
        val name = resolution.name
        for (r in configuration.resolutions) {
            if (r.name == name) {
                logger.error("Cannot add resolution \"$name\". Resolution with this name already exists.")
                return
            }
        }

        // TODO("refactor")
        for (day in timeline.days) {
            day.add(CompletionStatus.UNKNOWN)
        }

        configuration.resolutions.add(resolution)
        logger.debug("Resolution \"$name\" added successfully.")
    }

    fun removeResolution(name: String): Unit {
        val index = configuration.resolutions.indexOfFirst { it.name == name }
        if (index == -1) {
            logger.error("Cannot remove resolution \"$name\". Resolution with this name does not exist.")
            return
        }

        // TODO("refactor")
        for (day in timeline.days) {
            day.removeAt(index)
        }

        configuration.resolutions.removeAt(index)
        logger.debug("Resolution \"$name\" removed successfully.")
    }

    fun modifyResolution(): Unit {
        TODO("enable changing name, description, and path")
    }

    fun moveResolution(from: Int, to: Int): Unit {
        if (from > configuration.resolutions.size || to > configuration.resolutions.size || from < 0 || to < 0 || from == to) {
            logger.error("Cannot move resolution to specified position.")
            logger.debug("Invalid indices: from = $from, to = $to.")
            return
        }

        // TODO("refactor")
        for (day in timeline.days) {
            val status = day[from]
            day.removeAt(from)
            day.add(to, status)
        }

        val resolution = configuration.resolutions[from]
        configuration.resolutions.removeAt(from)
        configuration.resolutions.add(to, resolution)
        logger.debug("Resolution moved successfully from position $from to $to.")
    }
}