package kg.geektech.last.data.repositories

import androidx.room.Database
import kg.geektech.last.model.ShopItemDbModel

@Database(entities = [ShopItemDbModel:: class ],version=1, exportSchema = false)
abstract class AppDataBase:  {
}