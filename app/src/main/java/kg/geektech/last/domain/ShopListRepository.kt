package kg.geektech.last.domain

import androidx.lifecycle.LiveData
import kg.geektech.last.model.ShopItem

interface ShopListRepository {
    fun addShopItem(shopItem: ShopItem)
    fun getShopList(): LiveData<List<ShopItem>>
    fun deleteShopItem(shopItem: ShopItem)
    fun editShopItem(shopItem: ShopItem)
    fun findShopItem(id:Int): ShopItem
}