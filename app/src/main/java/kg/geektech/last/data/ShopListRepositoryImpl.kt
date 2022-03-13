package kg.geektech.last.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kg.geektech.last.domain.ShopItem
import kg.geektech.last.domain.ShopListRepository
import kg.geektech.last.rpesentation.MainViewModel
import java.lang.RuntimeException
import kotlin.random.Random

class ShopListRepositoryImpl: ShopListRepository {


    private val shopList = sortedSetOf<ShopItem>({
        o1,o2->o1.id.compareTo(o2.id)
    })
    private val shopListLd = MutableLiveData<List<ShopItem>>()

     private val forEnabled = MutableLiveData<Boolean>()

    private var autoIncrement = 1
     init {
         for (i in 1..20){
             val item = ShopItem("name $i",i, Random.nextBoolean())
             addShopItem(item)
         }
     }

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
        val oldShopItem = findShopItem(shopItem.id)
        shopList.remove(oldShopItem)
        addShopItem(shopItem)
    }

    override fun findShopItem(id:Int):ShopItem {
        return shopList.find {
            it.id==id
        }?:throw RuntimeException("shama")

    }
}