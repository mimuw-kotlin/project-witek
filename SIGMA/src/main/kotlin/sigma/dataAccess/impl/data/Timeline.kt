package sigma.dataAccess.impl.data

import java.util.Date

class Timeline(
    private val startDate: Date
) {
    private var days: MutableList<Day> = mutableListOf()

    fun getDays(): MutableList<Day> {
        return days
    }
}