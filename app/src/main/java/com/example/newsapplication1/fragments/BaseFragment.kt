package com.example.newsapplication1.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.newsapplication1.R
import com.example.newsapplication1.RequestManager
import com.example.newsapplication1.adapters.ContentAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseFragment(private val context: Context): Fragment(){

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var contentRecyclerView: RecyclerView
    open val category:String=""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val rootView = inflater.inflate(R.layout.base_fragment, container, false)

        contentRecyclerView=rootView.findViewById(R.id.content_recycler_view)
        swipeRefreshLayout=rootView.findViewById(R.id.swipeRefreshLayout)


        showArticles()

        return rootView
    }

    private fun showArticles(){

        CoroutineScope(Dispatchers.Main).launch {
            val newsList = withContext(Dispatchers.IO) {
                val contentObject = RequestManager(category)
                contentObject.getEverything()
            }

            contentRecyclerView.adapter = ContentAdapter(context, newsList)
        }

    }



}