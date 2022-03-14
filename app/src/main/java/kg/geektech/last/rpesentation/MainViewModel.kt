package kg.geektech.last.rpesentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kg.geektech.last.data.repositories.ShopListRepositoryImpl
import kg.geektech.last.domain.*
import kg.geektech.last.model.ShopItem

class MainViewModel:ViewModel() {



    private val repository = ShopListRepositoryImpl()

    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditItemUseCase(repository)
    private val findShopItemUseCase = FindItemUseCase(repository)

    private val shopList = getShopListUseCase.getShopList()

    fun addShopItem(shopItem: ShopItem){
        addShopItemUseCase.addShopItem((shopItem))
    }

    fun getShopLiveData(): LiveData<List<ShopItem>>{
        return shopList
    }


    fun deleteItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun editItem(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)

    }

    fun findItem(id: Int): ShopItem {
        return findShopItemUseCase.findShopItem(id)
    }

}