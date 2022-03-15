package kg.geektech.last.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kg.geektech.last.App
import kg.geektech.last.data.ShopListMapper
import kg.geektech.last.model.ShopItem
import kg.geektech.last.domain.ShopListRepository
import java.lang.RuntimeException
import kotlin.random.Random

class ShopListRepositoryImpl: ShopListRepository {


//    private val shopList = sortedSetOf<ShopItem>({
//        o1,o2->o1.id.compareTo(o2.id)
//    })
//    private val shopListLd = MutableLiveData<List<ShopItem>>()
//
//     private val forEnabled = MutableLiveData<Boolean>()
//
//    private var autoIncrement = 1

    private val mapper = ShopListMapper()
     init {
         for (i in 1..5){
             addShopItem(ShopItem("name $i", count=i,false))
         }
     }

//    override fun addShopItem(shopItem: ShopItem) {
//        if(shopItem.id== ShopItem.UNDEFINED_ID){
//            shopItem.id = autoIncrement
//            autoIncrement++
//        }
//        shopList.add(shopItem)
//        update()
//    }

    override fun addShopItem(shopItem: ShopItem) {
        App.dataBase.shopListDao().addShopItem(mapper.mapEntityToDbModel(shopItem))
    }



    override fun getShopList(): LiveData<List<ShopItem>> =Transformations.map(
        App.dataBase.shopListDao().getShopList()
    ){
        mapper.mapListDbModelToListEntity(it)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        App.dataBase.shopListDao().deleteShopItem(shopItem.id)
    }

    override fun editShopItem(shopItem: ShopItem) {
        App.dataBase.shopListDao().updateShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override fun findShopItem(id:Int): ShopItem {
      return  mapper.mapDbModelToEntity(App.dataBase.shopListDao().getShopItem(id))
    }
}