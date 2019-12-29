package com.zulqarnain.testproject.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.zulqarnain.testproject.R
import com.zulqarnain.testproject.api.MyService
import com.zulqarnain.testproject.architecture.ViewModelFactory
import com.zulqarnain.testproject.di.BaseActivity
import com.zulqarnain.testproject.ui.DummyFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var retrofitService: MyService
    lateinit var vm: MainViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProviders.of(this@MainActivity,viewModelFactory).get(MainViewModel::class.java)

        vm.logLiveLog.observe(this, Observer {

            Log.d("MainActivity","log from response $it")
        })
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, DummyFragment())
            .commit()
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
