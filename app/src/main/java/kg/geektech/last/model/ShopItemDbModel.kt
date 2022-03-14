package kg.geektech.last.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="shop_item")
data class ShopItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean

)
