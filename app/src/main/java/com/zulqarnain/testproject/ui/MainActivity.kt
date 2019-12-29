package com.zulqarnain.testproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.zulqarnain.testproject.R
import com.zulqarnain.testproject.api.MyService
import com.zulqarnain.testproject.di.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var retrofitService: MyService

    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.
            beginTransaction()
            .add(R.id.fragment_container,DummyFragment()).commit()
//        val adapter = ViewPagerAdapter(supportFragmentManager)
//        vpCategory.adapter = adapter
//        tbOptions.setupWithViewPager(vpCategory)
//        adapter.notifyDataSetChanged()

    }


    class ViewPagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

        override fun getItem(position: Int): Fragment {
            return DummyFragment()
        }

        override fun getCount(): Int {

            return 1
        }
    }

}
