package sigma.dataAccess.impl.data

class Resolution(
    private val name: String,
    private val description: String? = null,
    private val pathToImage: String? = null
) {
    fun getName(): String {
        return name
    }
}