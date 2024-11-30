package sigma.businessLogic.impl

import sigma.dataAccess.impl.data.CompletionStatus
import sigma.dataAccess.impl.data.Resolution
import sigma.dataAccess.impl.data.Timeline

class ResolutionsManager(
    private var resolutions: MutableList<Resolution>,
    private var timeline: Timeline,
    private var timeManager: TimeManager
) {
    fun addResolution(resolution: Resolution): Unit {
        // validate
        for (r in resolutions) {
            if (r.getName() == resolution.getName()) {
                // TODO: display information "Cannot add resolution {name}. Resolution with this name already exists.
                return
            }
        }

        // TODO: refactor
        for (day in timeline.getDays()) {
            day.add(CompletionStatus.UNKNOWN)
        }

        resolutions.add(resolution)
    }

    fun removeResolution(name: String): Unit {
        val index = resolutions.indexOfFirst { it.getName() == name }
        if (index == -1) {
            // TODO: display information "Cannot remove resolution {name}. Resolution with this name does not exist.
            return
        }

        // TODO: refactor
        for (day in timeline.getDays()) {
            day.removeAt(index)
        }

        resolutions.removeAt(index)
    }

    fun modifyResolution(): Unit {
        // TODO: enable changing name, description, and path
    }

    fun moveResolution(from: Int, to: Int): Unit {
        if (from > resolutions.size || to > resolutions.size || from < 0 || to < 0 || from == to) {
            // TODO: display error message
            return
        }

        // TODO: refactor
        for (day in timeline.getDays()) {
            val status = day[from]
            day.removeAt(from)
            day.add(to, status)
        }

        val resolution = resolutions[from]
        resolutions.removeAt(from)
        resolutions.add(to, resolution)
    }
}