package com.zulqarnain.testproject.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zulqarnain.testproject.R
import com.zulqarnain.testproject.api.MyService
import com.zulqarnain.testproject.architecture.ViewModelFactory
import com.zulqarnain.testproject.architecture.db.todoDao
import com.zulqarnain.testproject.data.local.Todo
import com.zulqarnain.testproject.data.remote.StoreCategory
import com.zulqarnain.testproject.databinding.ActivityMainBinding
import com.zulqarnain.testproject.databinding.SingleRowTodoBinding
import com.zulqarnain.testproject.di.BaseActivity
import com.zulqarnain.testproject.ui.DummyFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var retrofitService: MyService
    lateinit var vm: MainViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var todoDao: todoDao


    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        initview()
    }

    private fun initview() {

        val list = ArrayList<StoreCategory>()
        val adapater= CategoryAdapter(this@MainActivity,list){}
        rvTodo.layoutManager = LinearLayoutManager(this)
        rvTodo.adapter  =adapater

        todoDao.getToDoList().observe(this@MainActivity, Observer {
            Log.d("MainActivity", "log from response $it")

        })

        addItem()
        vm = ViewModelProviders.of(this@MainActivity, viewModelFactory)
            .get(MainViewModel::class.java)

//        vm._insertResponse.observe(this, Observer {
//            Log.d("MainActivity", "log from response inserted $it")
//        })



        vm.liveResponse.observe(this, Observer {
            Log.e("MainActivity response", "log from response ger ${it.storeCategories?.size}")
            editText.setText(it.code.toString())
//            if(it!=null && it.storeCategories !=null)
             list.addAll(it.storeCategories as ArrayList<StoreCategory>)
            adapater.notifyDataSetChanged()
        })

//        vm.isLoadin{
//            progressbar.
//        }

//        supportFragmentManager.beginTransaction()
//            .add(R.id.fragment_container, DummyFragment())
//            .commit()
    }

    fun addItem() {
        btnAdd.setOnClickListener {
           vm.insertData(editText.text.toString())
        }
    }

    fun initPagerAdapter() {
        //        val adapter = ViewPagerAdapter(supportFragmentManager)
//        vpCategory.adapter = adapter
//        tbOptions.setupWithViewPager(vpCategory)
//        adapter.notifyDataSetChanged()
    }

    class ViewPagerAdapter(manager: FragmentManager) :
        FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return DummyFragment()
        }

        override fun getCount(): Int {

            return 1
        }
    }

    class CategoryAdapter(
        var ctx: Context,
        var list: ArrayList<StoreCategory>,
        val onClick: (todo: Todo) -> Unit
    ) :
        RecyclerView.Adapter<CategoryAdapter.ToDoViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ToDoViewHolder {

            val binding:SingleRowTodoBinding=DataBindingUtil.inflate(LayoutInflater.from(ctx),
                R.layout.single_row_todo, parent, false)

            return  ToDoViewHolder(binding)
        }

        override fun getItemCount(): Int {
            Log.e("adapter ","size ${list.size}")
            return list.size
        }

        override fun onBindViewHolder(holder:ToDoViewHolder, position: Int) {
            holder.bindView(list[position])
//            holder.itemView.
        }

        class ToDoViewHolder(itemView: SingleRowTodoBinding) : RecyclerView.ViewHolder(itemView.root) {

            lateinit var view:SingleRowTodoBinding
            init{
                view = itemView
            }
            fun bindView(todo: StoreCategory) {
                view.category= todo

            }
        }
    }

}
