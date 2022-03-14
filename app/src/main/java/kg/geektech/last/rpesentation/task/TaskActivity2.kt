package kg.geektech.last.rpesentation.task

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geektech.last.R
import kg.geektech.last.databinding.ActivityTask2Binding
import kg.geektech.last.model.ShopItem
import kg.geektech.last.rpesentation.DetailActivity
import kg.geektech.last.rpesentation.MainViewModel

class TaskActivity2 : AppCompatActivity() {

    private val binding: ActivityTask2Binding by viewBinding()
    private val viewModel: MainViewModel by viewModels()
   // private lateinit var adapterTask: TaskAdapter
    private lateinit var shopListAdapter: ShopListAdapter
    private lateinit var  shopItem: ShopItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task2)
        setUpRecyclerView()
        initObservers()
        initListener()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("",false)
                searchItem.collapseActionView()

                var lol=0
                if (p0 != null) {
                    lol = p0.toInt()
                }

                if(lol==shopItem.id){
                Toast.makeText(this@TaskActivity2,"$p0",Toast.LENGTH_LONG).show()}
                else  Toast.makeText(this@TaskActivity2,"такого ID нету",Toast.LENGTH_LONG).show()
                return true

            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {




        return true
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

        binding.fab.setOnClickListener{
            DetailActivity.start(this)
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