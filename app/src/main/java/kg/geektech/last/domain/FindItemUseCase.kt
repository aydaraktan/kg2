package kg.geektech.last.domain

class FindItemUseCase(private val repository: ShopListRepository) {
    fun findShopItem(shopItem: ShopItem){
        repository.findShopItem(shopItem)
    }
}