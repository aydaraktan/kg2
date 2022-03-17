package kg.geektech.last.rpesentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kg.geektech.last.R
import kg.geektech.last.databinding.ActivityDetailBinding
import kg.geektech.last.model.ShopItem

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initListeners()
    }

    private fun initListeners() {
        binding.btnAdd.setOnClickListener {
            viewModel.addShopItem(addItem())
            finish()
        }
    }

    private fun addItem(): ShopItem {
        val name = binding.etName.text.toString()
        val count = binding.etCount.text.toString()

        return if (name.isNotBlank() && count.isNotBlank()) {
            ShopItem(name, count.toInt(), false)
        } else if (name.isNotBlank() && count.isBlank()) {
            ShopItem(name, 7, false)
        } else if (name.isBlank() && count.isNotBlank()) {
            ShopItem("Name Default", count.toInt(), false)
        } else {
            ShopItem("Name Default", 7, false)
        }

    }

    companion object{
        fun start(context: Context){
            context.startActivity(Intent(context, DetailActivity::class.java))
        }
    }
}