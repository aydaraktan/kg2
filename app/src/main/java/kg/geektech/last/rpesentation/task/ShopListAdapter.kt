package kg.geektech.last.rpesentation.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.last.R
import kg.geektech.last.model.ShopItem
import androidx.recyclerview.widget.ListAdapter

class ShopListAdapter: ListAdapter<ShopItem, ShopListAdapter.ViewHolder>(ShopItemDiffCallback()) {
    var onShopItemClick: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.shop_list_disable
            VIEW_TYPE_ENABLED -> R.layout.shop_list_enable
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.onBind(shopItem)
        holder.itemView.setOnClickListener{
            onShopItemClick?.invoke(shopItem)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val title: TextView = itemView.findViewById(R.id.tv_name)
        private val count: TextView = itemView.findViewById(R.id.tv_count)

        fun onBind(shopItem: ShopItem) {
            title.text = shopItem.name
            count.text = shopItem.count.toString()
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (!getItem(position).enabled) {
            VIEW_TYPE_DISABLED
        } else {
            VIEW_TYPE_ENABLED
        }
    }
    companion object {
        const val VIEW_TYPE_DISABLED = 100
        const val VIEW_TYPE_ENABLED = 101
    }
}