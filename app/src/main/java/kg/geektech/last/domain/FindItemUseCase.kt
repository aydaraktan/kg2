package kg.geektech.last.domain

import kg.geektech.last.model.ShopItem


class FindItemUseCase(private val repository: ShopListRepository) {

    suspend fun findShopItem(id: Int): ShopItem {
       return repository.findShopItem(id)
    }
}