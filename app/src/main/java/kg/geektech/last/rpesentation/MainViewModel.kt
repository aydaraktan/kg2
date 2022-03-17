package kg.geektech.last.rpesentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.geektech.last.data.repositories.ShopListRepositoryImpl
import kg.geektech.last.domain.*
import kg.geektech.last.model.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ShopListRepositoryImpl):ViewModel() {





    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditItemUseCase(repository)
    private val findShopItemUseCase = FindItemUseCase(repository)

    private val shopList = getShopListUseCase.getShopList()



    fun addShopItem(shopItem: ShopItem){
        viewModelScope.launch {
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun getShopLiveData(): LiveData<List<ShopItem>>{
        return shopList
    }


    fun deleteItem(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

    fun editItem(shopItem: ShopItem) {
        val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
        viewModelScope.launch {
            editShopItemUseCase.editShopItem(newShopItem)
        }
    }

    suspend fun findItem(id: Int): ShopItem {
        return findShopItemUseCase.findShopItem(id)
    }
}