package kg.geektech.last.rpesentation.main


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geektech.last.R
import kg.geektech.last.databinding.ActivityMainBinding
import kg.geektech.last.domain.ShopItem
import kg.geektech.last.rpesentation.MainViewModel

class MainActivity() : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initObservers()
       // initListeners()
    }

    private fun initObservers() {
        viewModel.getShopLiveData().observe(this){
            Log.e("TAG", "get: $it", )
        }
    }


    private fun initListeners() {
        binding.createItem.setOnClickListener{
            viewModel.addShopItem(addItem())
        }

        binding.deleteItemBtn.setOnClickListener{
            viewModel.deleteItem(ShopItem("potato", 2, false, binding.forIdEt.text.toString().toInt())
            )
        }

        binding.getChangeBtn.setOnClickListener{

            viewModel.editItem(ShopItem("potato",2,false,binding.forIdEt.text.toString().toInt()))

        }

//        binding.getFindBtn.setOnClickListener{
//            viewModel.findItem(ShopItem("potato",2,false,binding.forIdEt.text.toString().toInt()))
//        }
    }

    private fun addItem(): ShopItem {

        Log.e("TAG", "add:", )
        val text = binding.forIdEt.text.toString()

        return if (text.isEmpty()) {
            ShopItem("potato", 2, false)
        } else {
            ShopItem("potato", 2, false,text.toInt())
        }
    }
}