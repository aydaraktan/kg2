package kg.geektech.last.data

import kg.geektech.last.model.ShopItem
import kg.geektech.last.model.ShopItemDbModel

class ShopListMapper {

    fun mapEntityToDbModel(shopItem: ShopItem) = ShopItemDbModel(
        id = shopItem.id,
        name = shopItem.name,
        count = shopItem.count,
        enabled = shopItem.enabled
    )
    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel)=ShopItem(
        id = shopItemDbModel.id,
        name = shopItemDbModel.name,
        count = shopItemDbModel.count,
        enabled = shopItemDbModel.enabled
    )
    fun mapListDbModelToListEntity(list: List<ShopItemDbModel>)=list.map {
        mapDbModelToEntity(it)
    }
}