package kg.geektech.last.rpesentation.task

import androidx.recyclerview.widget.DiffUtil
import kg.geektech.last.model.ShopItem

class ShopItemDiffCallback: DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem) = oldItem.id==newItem.id

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem)= oldItem==newItem
}