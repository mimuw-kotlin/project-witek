package sigma.dataAccess.model.data

import sigma.dataAccess.impl.data.CompletionStatus

interface IDay {
    fun getResults(): List<CompletionStatus>
    fun add(completionStatus: CompletionStatus)
    fun add(index: Int, completionStatus: CompletionStatus)
    fun removeAt(index: Int)
    operator fun get(index: Int): CompletionStatus
}