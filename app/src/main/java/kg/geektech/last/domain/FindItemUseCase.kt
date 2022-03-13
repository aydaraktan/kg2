package kg.geektech.last.domain



class FindItemUseCase(private val repository: ShopListRepository) {

    fun findShopItem(id: Int): ShopItem{
       return repository.findShopItem(id)
    }
}