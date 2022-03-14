package kg.geektech.last.domain

import kg.geektech.last.model.ShopItem

class DeleteShopItemUseCase(private val repository: ShopListRepository) {
    fun deleteShopItem(shopItem: ShopItem){
        repository.deleteShopItem(shopItem)
    }

}