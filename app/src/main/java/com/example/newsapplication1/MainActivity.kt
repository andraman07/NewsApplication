package com.example.newsapplication1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.newsapplication1.adapters.ViewPagerAdapter
import com.example.newsapplication1.fragments.BusinessFragment
import com.example.newsapplication1.fragments.EntertainmentFragment
import com.example.newsapplication1.fragments.HealthFragment
import com.example.newsapplication1.fragments.HomeFragment
import com.example.newsapplication1.fragments.ScienceFragment
import com.example.newsapplication1.fragments.SportsFragment
import com.example.newsapplication1.fragments.TechnologyFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager:ViewPager
    private lateinit var tabLayout:TabLayout
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar=findViewById(R.id.custom_toolbar)
        viewPager=findViewById(R.id.view_pager)
        tabLayout=findViewById(R.id.tab_layout)
        setSupportActionBar(toolbar)

        viewPager.offscreenPageLimit = 5

        tabLayout.setupWithViewPager(viewPager)
        val viewPagerAdapter= ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragments("Home", HomeFragment(this))
        viewPagerAdapter.addFragments("Business",BusinessFragment(this))
        viewPagerAdapter.addFragments("Entertainment",EntertainmentFragment(this))
        viewPagerAdapter.addFragments("Health", HealthFragment(this))
        viewPagerAdapter.addFragments("Science", ScienceFragment(this))
        viewPagerAdapter.addFragments("Sports", SportsFragment(this))
        viewPagerAdapter.addFragments("Technology",TechnologyFragment(this))

        viewPager.adapter=viewPagerAdapter

    }




}