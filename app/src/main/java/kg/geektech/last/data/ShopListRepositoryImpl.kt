package kg.geektech.last.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kg.geektech.last.domain.ShopItem
import kg.geektech.last.domain.ShopListRepository
import kg.geektech.last.rpesentation.MainViewModel
 class ShopListRepositoryImpl: ShopListRepository {


    private val shopList = mutableListOf<ShopItem>()

    private val shopListLd = MutableLiveData<List<ShopItem>>()

     private val forEnabled = MutableLiveData<Boolean>()

    private var autoIncrement = 0

    override fun addShopItem(shopItem: ShopItem) {
        if(shopItem.id==ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrement
            autoIncrement++
        }
        shopList.add(shopItem)
        update()
    }



    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLd
    }



    private fun update(){
        shopListLd.value = shopList.toList()
    }


    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        update()
    }

    override fun editShopItem(shopItem: ShopItem) {
        shopItem.enabled=true
        shopList[shopItem.id] = shopItem
        update()
    }

    override fun findShopItem(shopItem: ShopItem) {
        shopList[shopItem.id]
        Log.e("TAG", "findShopItem: $shopItem", )
    }
}