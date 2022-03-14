package kg.geektech.last.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kg.geektech.last.model.ShopItemDbModel

@Dao
interface ShopDao {

@Query("SELECT * FROM shop_item")
fun getShopList(): LiveData<List<ShopItemDbModel>>

@Insert(onConflict = OnConflictStrategy.REPLACE)
fun addShopItem(shopItemDbModel: ShopItemDbModel)

@Query("SELECT * FROM shop_item WHERE id=:shopItemId LIMIT 1")
fun getShopItem (shopItemId: Int): ShopItemDbModel

@Query("DELETE FROM shop_item WHERE id = :shopItemId")
fun deleteShopItem(shopItemId: Int)

@Update
fun updateShopItem(shopItemDbModel: ShopItemDbModel)
}