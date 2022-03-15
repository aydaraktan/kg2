package kg.geektech.last.domain

import kg.geektech.last.model.ShopItem


class AddShopItemUseCase(private val repository: ShopListRepository) {
    suspend  fun addShopItem(shopItem: ShopItem){
        repository.addShopItem(shopItem)
    }
}