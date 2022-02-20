package kg.geektech.last.domain

data class ShopItem(
    val name: String,
    val count: Int,
    var enabled: Boolean = UNDEFINED_ENABLED,
    var id: Int = UNDEFINED_ID
)

{
    companion object{
        const val UNDEFINED_ID=0
        const val UNDEFINED_ENABLED=false
    }
}
