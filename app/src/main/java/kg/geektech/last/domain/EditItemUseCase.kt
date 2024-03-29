package kg.geektech.last.domain

import kg.geektech.last.model.ShopItem

class EditItemUseCase(private val repository: ShopListRepository) {
    suspend  fun editShopItem(shopItem: ShopItem){
        repository.editShopItem(shopItem)
    }
}