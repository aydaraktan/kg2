package kg.geektech.last.rpesentation.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geektech.last.R
import kg.geektech.last.databinding.ActivityTask2Binding
import kg.geektech.last.rpesentation.MainViewModel

class TaskActivity2 : AppCompatActivity() {

    private val binding: ActivityTask2Binding by viewBinding()
    private val viewModel: MainViewModel by viewModels()
   // private lateinit var adapterTask: TaskAdapter
    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task2)
        setUpRecyclerView()
        initObservers()
        initListener()
    }

    private fun initListener() {
        shopListAdapter.onShopItemClick ={
            Toast.makeText(
                applicationContext,
                "Shop item with id ${it.id} isEnabled: ${it.enabled}",
                Toast.LENGTH_SHORT
            ).show()

            viewModel.editItem(it)
        }
    }

    private fun initObservers() {
        viewModel.getShopLiveData().observe(this){
           // adapterTask.list=it
            shopListAdapter.submitList(it)
        }
    }

    private fun setUpRecyclerView() {
      //  adapterTask = TaskAdapter()
        shopListAdapter = ShopListAdapter()
        binding.taskRv.apply {
         //   adapter=adapterTask
            adapter=shopListAdapter
        }
        setUpSwipeListener(binding.taskRv)
    }

    private fun setUpSwipeListener(taskRv: RecyclerView) {
        val callback = object: ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        )
        {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               // val item = adapterTask.list[viewHolder.absoluteAdapterPosition]
                val item = shopListAdapter.currentList[viewHolder.absoluteAdapterPosition]
                    viewModel.deleteItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(taskRv)
    }


}