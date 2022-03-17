package kg.geektech.last.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kg.geektech.last.App
import kg.geektech.last.data.ShopDao
import kg.geektech.last.data.ShopListMapper
import kg.geektech.last.model.ShopItem
import kg.geektech.last.domain.ShopListRepository
import java.lang.RuntimeException
import javax.inject.Inject
import kotlin.random.Random

class ShopListRepositoryImpl @Inject constructor(private val shopDao: ShopDao):
    ShopListRepository  {


//    private val shopList = sortedSetOf<ShopItem>({
//        o1,o2->o1.id.compareTo(o2.id)
//    })
//    private val shopListLd = MutableLiveData<List<ShopItem>>()
//
//     private val forEnabled = MutableLiveData<Boolean>()
//
//    private var autoIncrement = 1

    private val mapper = ShopListMapper()
//     init {
//         for (i in 1..5){
//             addShopItem(ShopItem("name $i", count=i,false))
//         }
//     }

//    override fun addShopItem(shopItem: ShopItem) {
//        if(shopItem.id== ShopItem.UNDEFINED_ID){
//            shopItem.id = autoIncrement
//            autoIncrement++
//        }
//        shopList.add(shopItem)
//        update()
//    }

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }



    override fun getShopList(): LiveData<List<ShopItem>> =Transformations.map(
        shopDao.getShopList()
    ){
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopDao.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopDao.updateShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun findShopItem(id:Int): ShopItem {
      return  mapper.mapDbModelToEntity(shopDao.getShopItem(id))
    }
}