package com.zulqarnain.testproject.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.zulqarnain.testproject.R
import com.zulqarnain.testproject.api.MyService
import com.zulqarnain.testproject.architecture.ViewModelFactory
import com.zulqarnain.testproject.architecture.db.todoDao
import com.zulqarnain.testproject.data.local.Country
import com.zulqarnain.testproject.di.BaseActivity
import com.zulqarnain.testproject.ui.DummyFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var retrofitService: MyService
    lateinit var vm: MainViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var todoDao: todoDao

    val scope = CoroutineScope(Dispatchers.Main + Job())
    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initview()
        getData()
    }

    private fun initview() {

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
            Log.e("MainActivity response", "log from response ger $it")
            editText.setText(it.code.toString())
        })

//        vm.isLoadin{
//            progressbar.
//        }

//        supportFragmentManager.beginTransaction()
//            .add(R.id.fragment_container, DummyFragment())
//            .commit()
    }

    private fun getData() {
        scope.launch {
            withContext(Dispatchers.IO) {
                val response = retrofitService.getReponse().execute()
                val tmp = (response.body()!!.get("body") as JsonObject).
                    getAsJsonArray("countries")
                val type = object : TypeToken<ArrayList<Country>>() {}.type
                val list: ArrayList<Country>
                list = Gson().fromJson(Gson().toJson(tmp), type)
                Log.e("MainActivity response", "$list")


            }
        }
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


}
