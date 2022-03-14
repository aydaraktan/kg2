package kg.geektech.last.domain

import kg.geektech.last.model.ShopItem

class EditItemUseCase(private val repository: ShopListRepository) {
    fun editShopItem(shopItem: ShopItem){
        repository.editShopItem(shopItem)
    }
}