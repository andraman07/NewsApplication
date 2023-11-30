package com.example.newsapplication1.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import java.util.ArrayList

class ViewPagerAdapter(fm:androidx.fragment.app.FragmentManager):FragmentPagerAdapter(fm) {

    private val fragmentArrayList= ArrayList<Fragment>()
    private val fragmentTitleArrayList =ArrayList<String>()

    override fun getCount(): Int {
        return fragmentArrayList.size

    }


    override fun getItem(position: Int): Fragment {
              return fragmentArrayList[position]
    }

    override fun getPageTitle(position: Int):String {
        return fragmentTitleArrayList[position]
    }

    fun addFragments(title:String,fragments:Fragment){
           fragmentArrayList.add(fragments)
           fragmentTitleArrayList.add(title)
    }

}