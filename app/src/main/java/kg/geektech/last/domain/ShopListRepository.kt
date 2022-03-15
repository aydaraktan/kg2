package kg.geektech.last.domain

import androidx.lifecycle.LiveData
import kg.geektech.last.model.ShopItem

interface ShopListRepository {
    suspend fun addShopItem(shopItem: ShopItem)
    fun getShopList(): LiveData<List<ShopItem>>
    suspend  fun deleteShopItem(shopItem: ShopItem)
    suspend fun editShopItem(shopItem: ShopItem)
    suspend fun findShopItem(id:Int): ShopItem
}