package kg.geektech.last.domain

import androidx.lifecycle.LiveData
import kg.geektech.last.model.ShopItem

class GetShopListUseCase(private val repository: ShopListRepository) {
    fun getShopList(): LiveData<List<ShopItem>> {
        return repository.getShopList()
    }
}