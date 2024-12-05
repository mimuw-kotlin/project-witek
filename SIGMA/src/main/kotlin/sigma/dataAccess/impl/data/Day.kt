package sigma.dataAccess.impl.data

class Day {
    private var results: MutableList<CompletionStatus> = mutableListOf()

    fun getResults(): List<CompletionStatus> {
        return results.toList()
    }

    fun add(completionStatus: CompletionStatus) {
        results.add(completionStatus)
    }

    fun add(index: Int, completionStatus: CompletionStatus) {
        results.add(index, completionStatus)
    }

    fun removeAt(index: Int) {
        results.removeAt(index)
    }

    operator fun get(index: Int): CompletionStatus {
        return results[index]
    }

    companion object {
        fun createEmptyDay(resolutionsCount: Int): Day {
            val day = Day()
            repeat(resolutionsCount) { day.add(CompletionStatus.UNKNOWN) }
            return day
        }
    }
}
